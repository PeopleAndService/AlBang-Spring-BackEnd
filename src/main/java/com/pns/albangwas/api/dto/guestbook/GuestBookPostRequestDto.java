package com.pns.albangwas.api.dto.guestbook;

import com.pns.albangwas.domain.guestbook.GuestBook;
import com.pns.albangwas.domain.guestbook.GuestBookState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GuestBookPostRequestDto {

    private String content;
    private String anchor;
    private Long userId;
    private Long landmarkId;

    @Builder
    public GuestBookPostRequestDto(String content, String anchor) {
        this.content = content;
        this.anchor = anchor;
    }

    public GuestBook toEntity() {
        return GuestBook.builder()
                .content(this.content)
                .anchor(this.anchor)
                .state(GuestBookState.ACTIVE)
                .build();
    }
}
