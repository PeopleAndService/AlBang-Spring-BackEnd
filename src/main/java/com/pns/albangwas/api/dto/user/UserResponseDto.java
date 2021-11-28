package com.pns.albangwas.api.dto.user;

import com.pns.albangwas.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String googleId;
    private String email;
    private String name;
    private String nickname;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.googleId = user.getGoogleId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
    }
}
