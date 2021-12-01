package com.pns.albangwas.service;

import com.pns.albangwas.domain.user.User;
import com.pns.albangwas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public User signIn(User user) {
        // 가입 중복 확인
        Optional<User> signInUser = userRepository.findByGoogleId(user.getGoogleId());

        if (signInUser.isEmpty()) {
            // 회원 가입
            userRepository.save(user);
        } else {
            // 가입되어 있으면 해당 유저 반환
            return signInUser.get();
        }
        return user;
    }

    // 자동 로그인 회원 검증
    public User login(Long id) {
        Optional<User> loginUser = userRepository.findById(id);

        if (loginUser.isEmpty()) {
            throw new EntityNotFoundException("유저를 찾을 수 없습니다.");
        } else {
            return loginUser.get();
        }
    }

    // 닉네임 검증
    public boolean validateNickname(String nickname) {
        Optional<User> hasNickname = userRepository.findByNickname(nickname);

        return hasNickname.isPresent();
    }

    // 닉네임 수정
    @Transactional
    public User updateNickname(Long id, String nickname) {
        Optional<User> findUser = userRepository.findById(id);

        if (findUser.isEmpty()) {
            throw new EntityNotFoundException("유저를 찾을 수 없습니다.");
        } else {
            findUser.get().updateNickname(nickname);
        }

        return findUser.get();
    }

    // 회원 탈퇴
    @Transactional
    public void withdraw(Long id) {
        Optional<User> loginUser = userRepository.findById(id);

        if (loginUser.isEmpty()) {
            throw new EntityNotFoundException("유저를 찾을 수 없습니다.");
        } else {
            userRepository.delete(loginUser.get());
        }
    }
}
