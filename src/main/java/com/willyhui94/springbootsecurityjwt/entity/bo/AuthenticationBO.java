package com.willyhui94.springbootsecurityjwt.entity.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonPropertyOrder({"accessToken", "refreshToken", "limitedUserInfo"})
public class AuthenticationBO {

  private String accessToken;
  private String refreshToken;

  @JsonProperty("userInfo")
  private LimitedUserInfoBO limitedUserInfoBO;
}
