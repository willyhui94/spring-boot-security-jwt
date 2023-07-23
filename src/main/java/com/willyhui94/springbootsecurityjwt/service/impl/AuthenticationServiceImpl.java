package com.willyhui94.springbootsecurityjwt.service.impl;

import com.willyhui94.springbootsecurityjwt.constant.GlobalCodeEnum;
import com.willyhui94.springbootsecurityjwt.entity.Role;
import com.willyhui94.springbootsecurityjwt.entity.User;
import com.willyhui94.springbootsecurityjwt.entity.UserToken;
import com.willyhui94.springbootsecurityjwt.entity.bo.AuthenticationBO;
import com.willyhui94.springbootsecurityjwt.entity.bo.LimitedUserInfoBO;
import com.willyhui94.springbootsecurityjwt.entity.dto.CheckAuthenticationDTO;
import com.willyhui94.springbootsecurityjwt.entity.dto.RefreshTokenDTO;
import com.willyhui94.springbootsecurityjwt.entity.dto.RegisterUserDTO;
import com.willyhui94.springbootsecurityjwt.exception.ServiceException;
import com.willyhui94.springbootsecurityjwt.repository.UserRepository;
import com.willyhui94.springbootsecurityjwt.repository.UserTokenRepository;
import com.willyhui94.springbootsecurityjwt.service.AuthenticationService;
import com.willyhui94.springbootsecurityjwt.service.JwtTokenService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenService jwtTokenService;
  private final UserRepository userRepository;
  private final UserTokenRepository userTokenRepository;

  @PostConstruct
  public void initDB() {
    User user =
        User.builder()
            .firstName("first name")
            .lastName("last name")
            .email("example@test.com")
            .password(this.passwordEncoder.encode("password"))
            .role(Role.ROLE_USER)
            .build();
    this.userRepository.save(user);
  }

  @Override
  public AuthenticationBO registerUser(RegisterUserDTO registerUserDTO) {
    User user =
        User.builder()
            .firstName(registerUserDTO.getFirstName())
            .lastName(registerUserDTO.getLastName())
            .email(registerUserDTO.getEmail())
            .password(this.passwordEncoder.encode(registerUserDTO.getPassword()))
            .role(Role.ROLE_USER)
            .build();
    this.userRepository.save(user);

    AuthenticationBO authenticationBO = buildAuthenticationBO(user);

    UserToken userToken =
        UserToken.builder()
            .userId(user.getId())
            .accessToken(authenticationBO.getAccessToken())
            .refreshToken(authenticationBO.getRefreshToken())
            .expiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .build();
    this.userTokenRepository.save(userToken);
    return authenticationBO;
  }

  @Override
  public AuthenticationBO checkAuthentication(CheckAuthenticationDTO checkAuthenticationDTO) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            checkAuthenticationDTO.getEmail(), checkAuthenticationDTO.getPassword()));
    User user = this.userRepository.findByEmail(checkAuthenticationDTO.getEmail()).orElseThrow();
    return buildAuthenticationBO(user);
  }

  @Override
  public AuthenticationBO refreshToken(RefreshTokenDTO RefreshTokenDTO) {
    UserToken userToken =
        this.userTokenRepository
            .findByRefreshToken(RefreshTokenDTO.getRefreshToken())
            .orElseThrow(() -> new ServiceException(GlobalCodeEnum.GLOBAL_FAIL_90003));

    if (userToken.getExpiryDate().before(new Date(System.currentTimeMillis()))) {
      throw new ServiceException(GlobalCodeEnum.GLOBAL_FAIL_90003);
    }

    User user =
        this.userRepository
            .findById(userToken.getUserId())
            .orElseThrow(() -> new ServiceException(GlobalCodeEnum.GLOBAL_FAIL_90003));

    this.userTokenRepository.deleteById(userToken.getId());

    AuthenticationBO authenticationBO = buildAuthenticationBO(user);

    UserToken newUserToken =
        UserToken.builder()
            .userId(user.getId())
            .accessToken(authenticationBO.getAccessToken())
            .refreshToken(authenticationBO.getRefreshToken())
            .expiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .build();
    this.userTokenRepository.save(newUserToken);
    return authenticationBO;
  }

  private AuthenticationBO buildAuthenticationBO(User user) {
    String accessToken = this.jwtTokenService.generateAccessToken(user, new HashMap<>());
    String refreshToken = this.jwtTokenService.generateRefreshToken(user);
    LimitedUserInfoBO limitedUserInfoBO =
        LimitedUserInfoBO.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    return AuthenticationBO.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .limitedUserInfoBO(limitedUserInfoBO)
        .build();
  }
}
