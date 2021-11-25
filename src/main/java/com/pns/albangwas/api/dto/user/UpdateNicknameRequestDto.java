package com.pns.albangwas.api.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateNicknameRequestDto {

    private String nickname;

    public UpdateNicknameRequestDto(String nickname) {
        this.nickname = nickname;
    }
}
