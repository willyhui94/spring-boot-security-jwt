package com.willyhui94.springbootsecurityjwt.exception;

import com.willyhui94.springbootsecurityjwt.constant.GlobalCodeEnum;
import com.willyhui94.springbootsecurityjwt.entity.ResponseResult;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

  @ExceptionHandler(ServiceException.class)
  @ResponseBody
  public ResponseResult processServiceException(HttpServletResponse response, ServiceException e) {
    // Set HTTP status and HTTP header
    response.setStatus(e.getHttpStatus().value());
    response.setContentType(CONTENT_TYPE);

    // Set response data
    ResponseResult responseResult = new ResponseResult();
    responseResult.setCode(e.getCode());
    responseResult.setMessage(e.getMessage());

    log.info("GlobalExceptionHandler.processServiceException: {}", e.getMessage());
    return responseResult;
  }

  @ExceptionHandler(Exception.class)
  public ResponseResult processDefaultException(HttpServletResponse response, Exception e) {
    // Set HTTP status and HTTP header
    response.setStatus(GlobalCodeEnum.GLOBAL_FAIL_90000.getHttpStatus().value());
    response.setContentType(CONTENT_TYPE);

    // Set response data
    ResponseResult responseResult =
        ResponseResult.systemException(GlobalCodeEnum.GLOBAL_FAIL_90000);

    log.info("GlobalExceptionHandler.processDefaultException: {}", e.getMessage());
    return responseResult;
  }
}
