package com.willyhui94.springbootsecurityjwt.exception;

import com.willyhui94.springbootsecurityjwt.constant.BusinessCodeEnum;
import com.willyhui94.springbootsecurityjwt.constant.GlobalCodeEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString(callSuper = true)
public class ServiceException extends RuntimeException {

  private final Integer code;
  private final HttpStatus httpStatus;

  public ServiceException(BusinessCodeEnum businessCodeEnum) {
    super(businessCodeEnum.getDescription());
    this.code = businessCodeEnum.getCode();
    this.httpStatus = businessCodeEnum.getHttpStatus();
  }

  public ServiceException(GlobalCodeEnum globalCodeEnum) {
    super(globalCodeEnum.getDescription());
    this.code = globalCodeEnum.getCode();
    this.httpStatus = globalCodeEnum.getHttpStatus();
  }
}
