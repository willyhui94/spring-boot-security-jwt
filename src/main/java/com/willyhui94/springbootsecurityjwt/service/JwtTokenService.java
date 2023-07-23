package com.willyhui94.springbootsecurityjwt.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtTokenService {

  String generateAccessToken(UserDetails userDetails, Map<String, Object> extraClaims);

  String generateRefreshToken(UserDetails userDetails);

  boolean isTokenValid(String token, UserDetails userDetails);

  String extractUsername(String token);
}
