package com.pns.albangwas.api.dto.guestbook;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class GuestBookGetResponseDto {

    private int numOfGuestBooks;
    private List<GuestBookNormalResponseDto> results;

    public GuestBookGetResponseDto(List<GuestBookNormalResponseDto> results) {
        this.numOfGuestBooks = results.size();
        this.results = results;
    }
}
