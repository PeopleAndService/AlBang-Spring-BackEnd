package com.pns.albangwas.api.dto.user;

import com.pns.albangwas.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInRequestDto {

    private String googleId;
    private String email;
    private String name;

    @Builder
    public UserSignInRequestDto(String googleId, String email, String name) {
        this.googleId = googleId;
        this.email = email;
        this.name = name;
    }

    public User toEntity() {
        return User.builder()
                .googleId(this.googleId)
                .email(this.email)
                .name(this.name)
                .build();
    }
}
