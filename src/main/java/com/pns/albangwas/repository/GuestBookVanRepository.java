package com.pns.albangwas.repository;

import com.pns.albangwas.domain.guestbook.GuestBookVan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookVanRepository extends JpaRepository<GuestBookVan, Long> {


}
