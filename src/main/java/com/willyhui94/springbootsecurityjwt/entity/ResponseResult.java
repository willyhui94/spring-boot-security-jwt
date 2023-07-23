package com.willyhui94.springbootsecurityjwt.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.willyhui94.springbootsecurityjwt.constant.GlobalCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@JsonPropertyOrder({"code", "message", "data"})
public class ResponseResult<T> implements Serializable {

  private static final long serialVersionID = 1L;

  private Integer code;

  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public static <T> ResponseResult<T> ok() {
    return packageResponseResult(null, GlobalCodeEnum.GLOBAL_SUCCESS_10000);
  }

  public static <T> ResponseResult<T> ok(T data) {
    return packageResponseResult(data, GlobalCodeEnum.GLOBAL_SUCCESS_10000);
  }

  public static <T> ResponseResult<T> systemException(GlobalCodeEnum globalCodeEnum) {
    return packageResponseResult(null, globalCodeEnum);
  }

  public static <T> ResponseResult<T> systemException(T data, GlobalCodeEnum globalCodeEnum) {
    return packageResponseResult(data, globalCodeEnum);
  }

  private static <T> ResponseResult<T> packageResponseResult(
      T data, GlobalCodeEnum globalCodeEnum) {
    ResponseResult<T> responseResult = new ResponseResult<>();
    responseResult.setCode(globalCodeEnum.getCode());
    responseResult.setMessage(globalCodeEnum.getDescription());
    responseResult.setData(data);
    return responseResult;
  }
}
