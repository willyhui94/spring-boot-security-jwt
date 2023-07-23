package com.willyhui94.springbootsecurityjwt.entity.bo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.willyhui94.springbootsecurityjwt.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonPropertyOrder({"firstName", "lastName", "email", "role"})
public class LimitedUserInfoBO {

  private String firstName;
  private String lastName;
  private String email;
  private Role role;
}
