package com.willyhui94.springbootsecurityjwt.entity;

import lombok.Getter;

@Getter
public enum Role {
  ROLE_USER("ROLE_USER"),
  ROLE_ADMIN("ROLE_ADMIN");

  private final String name;

  Role(String name) {
    this.name = name;
  }
}
