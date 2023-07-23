package com.willyhui94.springbootsecurityjwt.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckAuthenticationDTO {

  private String email;
  private String password;
}
