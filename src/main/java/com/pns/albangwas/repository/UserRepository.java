package com.pns.albangwas.repository;

import com.pns.albangwas.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

      // 가입된 사용자 확인
      Optional<User> findByGoogleId(String googleId);

      // 닉네임 중복 확인
      Optional<User> findByNickname(String nickname);
}
