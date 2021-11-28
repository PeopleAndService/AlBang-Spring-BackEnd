package com.pns.albangwas.service;

import com.pns.albangwas.domain.guestbook.GuestBook;
import com.pns.albangwas.domain.guestbook.GuestBookVan;
import com.pns.albangwas.domain.landmark.Landmark;
import com.pns.albangwas.domain.user.User;
import com.pns.albangwas.repository.GuestBookRepository;
import com.pns.albangwas.repository.GuestBookVanRepository;
import com.pns.albangwas.repository.LandmarkRepository;
import com.pns.albangwas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;
    private final LandmarkRepository landmarkRepository;
    private final GuestBookVanRepository guestBookVanRepository;

    @Transactional
    public GuestBook saveGuestBook(GuestBook guestBook, Long userId, Long landmarkId) {
        User author = userRepository.getById(userId);
        Landmark landmark = landmarkRepository.getById(landmarkId);

        guestBook.setUser(author);
        guestBook.setLandmark(landmark);

        guestBookRepository.save(guestBook);
        return guestBookRepository.getById(guestBook.getId());
    }

    public List<GuestBook> findGuestBooksByUserId(Long userId) {
        return guestBookRepository.findGuestBooksByUserId(userId);
    }

    public List<GuestBook> findGuestBooksByLandmarkId(Long landmarkId) {
        return guestBookRepository.findGuestBooksByLandmarkId(landmarkId);
    }

    @Transactional
    public void vanGuestBook(GuestBookVan guestBookVan, Long guestBookId, Long userId) {
        User reporter = userRepository.getById(userId);
        GuestBook guestBook = guestBookRepository.getById(guestBookId);

        guestBookVan.setUser(reporter);
        guestBookVan.setGuestBook(guestBook);

        guestBookVanRepository.save(guestBookVan);
    }
}
