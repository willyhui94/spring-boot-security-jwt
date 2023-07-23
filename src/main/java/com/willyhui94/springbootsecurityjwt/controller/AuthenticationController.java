package com.willyhui94.springbootsecurityjwt.controller;

import com.willyhui94.springbootsecurityjwt.entity.ResponseResult;
import com.willyhui94.springbootsecurityjwt.entity.bo.AuthenticationBO;
import com.willyhui94.springbootsecurityjwt.entity.dto.CheckAuthenticationDTO;
import com.willyhui94.springbootsecurityjwt.entity.dto.RefreshTokenDTO;
import com.willyhui94.springbootsecurityjwt.entity.dto.RegisterUserDTO;
import com.willyhui94.springbootsecurityjwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register-user")
  public ResponseResult<AuthenticationBO> registerUser(
      @Validated @RequestBody RegisterUserDTO registerUserDTO) {
    return ResponseResult.ok(authenticationService.registerUser(registerUserDTO));
  }

  @PostMapping("/check-authentication")
  public ResponseResult<AuthenticationBO> checkAuthentication(
      @Validated @RequestBody CheckAuthenticationDTO checkAuthenticationDTO) {
    return ResponseResult.ok(authenticationService.checkAuthentication(checkAuthenticationDTO));
  }

  @PostMapping("/refresh-token")
  public ResponseResult<AuthenticationBO> refreshToken(
      @Validated @RequestBody RefreshTokenDTO refreshTokenDTO) {
    log.info("refreshTokenDTO: {}", refreshTokenDTO);
    return ResponseResult.ok(authenticationService.refreshToken(refreshTokenDTO));
  }
}
