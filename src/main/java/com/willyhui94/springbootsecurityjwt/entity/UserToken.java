package com.willyhui94.springbootsecurityjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "t_user_token")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false, unique = true)
  private Integer userId;

  @Column(nullable = false, unique = true)
  private String accessToken;

  @Column(nullable = false, unique = true)
  private String refreshToken;

  @Column(nullable = false)
  private Date expiryDate;
}
