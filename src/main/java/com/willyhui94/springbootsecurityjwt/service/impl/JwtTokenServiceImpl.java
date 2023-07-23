package com.willyhui94.springbootsecurityjwt.service.impl;

import com.willyhui94.springbootsecurityjwt.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

  private final Integer EXPIRATION_DURATION = 1000 * 60 * 60;
  @Value("${jwt.secret-key}")
  private String SECRET_KEY;
  @Value("${jwt.issuer}")
  private String ISSUER;

  @Override
  public String generateAccessToken(UserDetails userDetails, Map<String, Object> extraClaims) {
    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuer(this.ISSUER)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + this.EXPIRATION_DURATION))
        .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public String generateRefreshToken(UserDetails userDetails) {
    return UUID.randomUUID().toString();
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = this.extractUsername(token);
    return !this.isTokenExpired(token) && username.equals(userDetails.getUsername());
  }

  @Override
  public String extractUsername(String token) {
    return this.extractClaim(token, Claims::getSubject);
  }

  private boolean isTokenExpired(String token) {
    return this.extractExpiration(token).before(new Date(System.currentTimeMillis()));
  }

  private Date extractExpiration(String token) {
    return this.extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = this.extractAllClaim(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaim(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(this.getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
