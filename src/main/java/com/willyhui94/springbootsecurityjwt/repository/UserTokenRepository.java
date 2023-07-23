package com.willyhui94.springbootsecurityjwt.repository;

import com.willyhui94.springbootsecurityjwt.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {

  Optional<UserToken> findByRefreshToken(String refreshToken);
}
