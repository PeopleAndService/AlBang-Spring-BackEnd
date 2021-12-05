package com.pns.albangwas.api.dto.guestbook;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class GuestBookGetResponseDto {

    private int count;
    private List<? extends GuestBookNormalResponseDto> results;

    public GuestBookGetResponseDto(List<? extends GuestBookNormalResponseDto> results) {
        this.count = results.size();
        this.results = results;
    }
}
