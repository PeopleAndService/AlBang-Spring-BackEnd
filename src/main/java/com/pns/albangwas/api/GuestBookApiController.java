package com.pns.albangwas.api;

import com.pns.albangwas.api.dto.guestbook.*;
import com.pns.albangwas.domain.guestbook.GuestBook;
import com.pns.albangwas.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class GuestBookApiController {

    private final GuestBookService guestBookService;

    @PostMapping("/guestbook/new")
    public GuestBookNormalResponseDto saveGuestBook(@RequestBody GuestBookPostRequestDto guestBookPostRequestDto) {
        GuestBook saved = guestBookPostRequestDto.toEntity();

        return new GuestBookNormalResponseDto(
                guestBookService.saveGuestBook(
                        saved,
                        guestBookPostRequestDto.getUserId(),
                        guestBookPostRequestDto.getLandmarkId()
                )
        );
    }

    @GetMapping("/guestbook/get/author")
    public GuestBookGetResponseDto findGuestBooksByAuthor(@RequestParam(name = "author") Long userId) {
        List<GuestBook> result = guestBookService.findGuestBooksByUserId(userId);

        return new GuestBookGetResponseDto(
                result.stream()
                        .map(GuestBookFindByUserResponseDto::new)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/guestbook/get/landmark")
    public GuestBookGetResponseDto findGuestBooksByLandmark(@RequestParam(name = "landmark") Long landmarkId) {
        List<GuestBook> result = guestBookService.findGuestBooksByLandmarkId(landmarkId);

        return new GuestBookGetResponseDto(
                result.stream()
                        .map(GuestBookFindByLandmarkResponseDto::new)
                        .collect(Collectors.toList())
        );
    }

    @DeleteMapping("guestbook/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuestbook(@PathVariable(name = "id") Long guestbookId) {
        guestBookService.deleteGuestbook(guestbookId);
    }
}
