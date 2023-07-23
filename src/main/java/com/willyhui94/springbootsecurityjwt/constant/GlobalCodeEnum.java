package com.willyhui94.springbootsecurityjwt.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalCodeEnum {
  GLOBAL_SUCCESS_10000(10000, "Success", HttpStatus.OK),
  GLOBAL_FAIL_90000(90000, "General internal service error", HttpStatus.INTERNAL_SERVER_ERROR),
  GLOBAL_FAIL_90001(90001, "Bad Credential", HttpStatus.FORBIDDEN),
  GLOBAL_FAIL_90002(90002, "Access token expired", HttpStatus.FORBIDDEN),
  GLOBAL_FAIL_90003(90003, "Refresh token error", HttpStatus.FORBIDDEN);

  private final Integer code;
  private final String description;
  private final HttpStatus httpStatus;

  GlobalCodeEnum(Integer code, String description, HttpStatus httpStatus) {
    this.code = code;
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
