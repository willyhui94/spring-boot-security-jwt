package com.willyhui94.springbootsecurityjwt.service;

import com.willyhui94.springbootsecurityjwt.entity.bo.AuthenticationBO;
import com.willyhui94.springbootsecurityjwt.entity.dto.CheckAuthenticationDTO;
import com.willyhui94.springbootsecurityjwt.entity.dto.RefreshTokenDTO;
import com.willyhui94.springbootsecurityjwt.entity.dto.RegisterUserDTO;

public interface AuthenticationService {

  AuthenticationBO registerUser(RegisterUserDTO registerUserDTO);

  AuthenticationBO checkAuthentication(CheckAuthenticationDTO checkAuthenticationDTO);

  AuthenticationBO refreshToken(RefreshTokenDTO RefreshTokenDTO);
}
