package com.pns.albangwas.api.dto.guestbook;

import com.pns.albangwas.domain.guestbook.GuestBook;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class GuestBookNormalResponseDto {

    private Long id;
    private String content;
    private String anchor;
    private String state;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public GuestBookNormalResponseDto(GuestBook guestBook) {
        this.id = guestBook.getId();
        this.content = guestBook.getContent();
        this.anchor = guestBook.getAnchor();
        this.state = guestBook.getState().name();
        this.createdTime = guestBook.getCreatedTime();
        this.updatedTime = guestBook.getUpdatedTime();
    }
}
