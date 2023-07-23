package com.willyhui94.springbootsecurityjwt.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessCodeEnum {
  BUSINESS_CODE_10000(10000, "success", HttpStatus.OK),
  BUSINESS_CODE_90001(90001, "User not found", HttpStatus.NOT_FOUND),
  BUSINESS_CODE_90002(90002, "Product not found", HttpStatus.NOT_FOUND);

  private final Integer code;
  private final String description;
  private final HttpStatus httpStatus;

  BusinessCodeEnum(Integer code, String description, HttpStatus httpStatus) {
    this.code = code;
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
