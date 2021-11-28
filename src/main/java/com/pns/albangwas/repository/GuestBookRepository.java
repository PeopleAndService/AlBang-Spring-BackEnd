package com.pns.albangwas.repository;

import com.pns.albangwas.domain.guestbook.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {

    List<GuestBook> findGuestBooksByUserId(Long userId);
    List<GuestBook> findGuestBooksByLandmarkId(Long landmarkId);
}
