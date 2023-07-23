package com.willyhui94.springbootsecurityjwt.service;

import com.willyhui94.springbootsecurityjwt.entity.bo.LimitedUserInfoBO;

public interface UserService {

  LimitedUserInfoBO getUserProfile();
}
