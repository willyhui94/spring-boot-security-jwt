package com.willyhui94.springbootsecurityjwt.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RefreshTokenDTO {

  private String refreshToken;
}
