package com.pns.albangwas.api;

import com.pns.albangwas.api.dto.user.UpdateNicknameRequestDto;
import com.pns.albangwas.api.dto.user.UserNormalResponseDto;
import com.pns.albangwas.api.dto.user.UserSignInRequestDto;
import com.pns.albangwas.domain.user.User;
import com.pns.albangwas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user/new")
    public UserNormalResponseDto signIn(@RequestBody UserSignInRequestDto requestBody){
        User result = userService.signIn(requestBody.toEntity());
        return new UserNormalResponseDto(result);
    }

    // 로그인
    @GetMapping("/user/login/{id}")
    public UserNormalResponseDto login(@PathVariable("id") Long id) {
        try {
            return new UserNormalResponseDto(userService.login(id));
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    // 닉네임 검증
    @GetMapping("/user")
    public Map<String, Boolean> validateNickname(@RequestParam("nickname") String nickname) {
        boolean isDuplicated = userService.validateNickname(nickname);
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("result", isDuplicated);
        return response;
    }

    // 닉네임 수정
    @PatchMapping("/user/nickname/{id}")
    public UserNormalResponseDto updateNickname(@PathVariable("id") Long id, @RequestBody UpdateNicknameRequestDto updateNicknameRequestDto) {
        return new UserNormalResponseDto(userService.updateNickname(id, updateNicknameRequestDto.getNickname()));
    }

    // 회원 탈퇴
    @DeleteMapping("/user/withdraw/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable("id") Long id) {
        try {
            userService.withdrawUser(id);
        } catch (NoSuchElementException exception) {

        }
    }
}
