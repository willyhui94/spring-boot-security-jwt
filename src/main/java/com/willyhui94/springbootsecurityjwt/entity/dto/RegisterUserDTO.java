package com.willyhui94.springbootsecurityjwt.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterUserDTO {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
