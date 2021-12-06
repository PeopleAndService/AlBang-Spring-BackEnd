package com.pns.albangwas.api.dto.guestbook;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GuestBookVanRequestDto {

    private Long userId;
    private Long guestBookId;

    public GuestBookVanRequestDto(Long userId, Long guestBookId) {
        this.userId = userId;
        this.guestBookId = guestBookId;
    }
}
