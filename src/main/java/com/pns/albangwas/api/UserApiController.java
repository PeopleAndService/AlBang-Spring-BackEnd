package com.pns.albangwas.api;

import com.pns.albangwas.api.dto.user.UpdateNicknameRequestDto;
import com.pns.albangwas.api.dto.user.UserResponseDto;
import com.pns.albangwas.api.dto.user.UserSignInRequestDto;
import com.pns.albangwas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user/new")
    public UserResponseDto signIn(@RequestBody UserSignInRequestDto requestBody){
        return new UserResponseDto(userService.signIn(requestBody.toEntity()));
    }

    // 로그인
    @GetMapping("/user/login/{id}")
    public UserResponseDto login(@PathVariable("id") Long id) {
        return new UserResponseDto(userService.login(id));
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
    public UserResponseDto updateNickname(@PathVariable("id") Long id, @RequestBody UpdateNicknameRequestDto updateNicknameRequestDto) {
        return new UserResponseDto(
                userService.updateNickname(id, updateNicknameRequestDto.getNickname())
        );
    }

    // 회원 탈퇴
    @DeleteMapping("/user/withdraw/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable("id") Long id) {
        userService.withdraw(id);
    }
}
