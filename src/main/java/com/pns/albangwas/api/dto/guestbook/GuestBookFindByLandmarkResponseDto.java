package com.pns.albangwas.api.dto.guestbook;

import com.pns.albangwas.domain.guestbook.GuestBook;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GuestBookFindByLandmarkResponseDto extends GuestBookNormalResponseDto {

    private String author;

    public GuestBookFindByLandmarkResponseDto(GuestBook guestBook) {
        super(guestBook);
        this.author = guestBook.getUser().getNickname();
    }
}
