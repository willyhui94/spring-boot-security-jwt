package com.willyhui94.springbootsecurityjwt.config.filter;

import com.willyhui94.springbootsecurityjwt.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final String AUTHORIZATION = "Authorization";
  private final String BEARER = "Bearer ";

  private final JwtTokenService jwtTokenService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader(AUTHORIZATION);
    final String jwtToken;
    final String userEmail;

    if (authHeader == null || !authHeader.startsWith(BEARER)) {
      log.info("checkpoint 00000");
      filterChain.doFilter(request, response);
      return;
    }

    jwtToken = authHeader.substring(BEARER.length());
    userEmail = this.jwtTokenService.extractUsername(jwtToken);
    log.info("user email: {}", userEmail);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      log.info("checkpoint 00001");
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

      if (this.jwtTokenService.isTokenValid(jwtToken, userDetails)) {
        log.info("checkpoint 00002");
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
