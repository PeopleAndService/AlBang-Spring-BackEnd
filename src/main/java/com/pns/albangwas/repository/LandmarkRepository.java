package com.pns.albangwas.repository;

import com.pns.albangwas.domain.landmark.Landmark;
import com.pns.albangwas.domain.landmark.LandmarkState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    List<Landmark> findAllByStateEquals(LandmarkState state);
}
