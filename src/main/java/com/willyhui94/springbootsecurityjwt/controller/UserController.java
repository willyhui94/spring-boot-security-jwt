package com.willyhui94.springbootsecurityjwt.controller;

import com.willyhui94.springbootsecurityjwt.entity.ResponseResult;
import com.willyhui94.springbootsecurityjwt.entity.bo.LimitedUserInfoBO;
import com.willyhui94.springbootsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/profile")
  @PreAuthorize("hasRole(T(com.willyhui94.springbootsecurityjwt.entity.Role).ROLE_USER)")
  public ResponseResult<LimitedUserInfoBO> getUserProfile() {
    return ResponseResult.ok(userService.getUserProfile());
  }
}
