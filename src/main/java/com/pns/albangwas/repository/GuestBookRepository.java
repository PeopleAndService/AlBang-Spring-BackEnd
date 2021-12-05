package com.pns.albangwas.repository;

import com.pns.albangwas.domain.guestbook.GuestBook;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {

    @EntityGraph(attributePaths = { "landmark" })
    List<GuestBook> findGuestBooksByUserId(Long userId);

    @EntityGraph(attributePaths = { "user" })
    List<GuestBook> findGuestBooksByLandmarkId(Long landmarkId);
}
