package com.willyhui94.springbootsecurityjwt.service.impl;

import com.willyhui94.springbootsecurityjwt.entity.User;
import com.willyhui94.springbootsecurityjwt.entity.bo.LimitedUserInfoBO;
import com.willyhui94.springbootsecurityjwt.repository.UserRepository;
import com.willyhui94.springbootsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public LimitedUserInfoBO getUserProfile() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    LimitedUserInfoBO limitedUserInfoBO =
        LimitedUserInfoBO.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    return limitedUserInfoBO;
  }
}
