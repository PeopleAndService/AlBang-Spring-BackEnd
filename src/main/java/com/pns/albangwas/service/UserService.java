package com.pns.albangwas.service;

import com.pns.albangwas.domain.user.User;
import com.pns.albangwas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 회원가입 + 로그인, 닉네임 검증, 닉네임 등록(수정), 회원 탈퇴

    // 회원가입
    @Transactional
    public User signIn(User user) {
        // validateDuplicateUser
        Optional<User> signInUser = userRepository.findByGoogleId(user.getGoogleId());
        if (signInUser.isEmpty()) {
            userRepository.save(user);
        } else {
            return signInUser.get();
        }
        return user;
    }

    // 로그인
    public User login(Long id) {
        Optional<User> loginUser = userRepository.findById(id);

        if (loginUser.isEmpty()) {
            throw new NoSuchElementException(id + " 유저를 찾을 수 없습니다.");
        } else {
            return loginUser.get();
        }
    }

    // 닉네임 검증
    public boolean validateNickname(String nickname) {
        Optional<User> hasNickname = userRepository.findByNickname(nickname);

        return hasNickname.isEmpty();
    }

    // 닉네임 수정
    @Transactional
    public User updateNickname(Long id, String nickname) {
        Optional<User> findUser = userRepository.findById(id);

        if (findUser.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            findUser.get().updateNickname(nickname);
        }

        return findUser.get();
    }

    // 회원 탈퇴
    @Transactional
    public void withdrawUser(Long id) {
        Optional<User> loginUser = userRepository.findById(id);

        if (loginUser.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            userRepository.delete(loginUser.get());
        }
    }
}
