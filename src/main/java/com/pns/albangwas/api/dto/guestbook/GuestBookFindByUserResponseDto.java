package com.pns.albangwas.api.dto.guestbook;

import com.pns.albangwas.domain.guestbook.GuestBook;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GuestBookFindByUserResponseDto extends GuestBookNormalResponseDto {

    private String landmarkName;

    public GuestBookFindByUserResponseDto(GuestBook guestBook) {
        super(guestBook);
        this.landmarkName = guestBook.getLandmark().getName();
    }
}
