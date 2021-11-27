package com.pns.albangwas.service;

import com.pns.albangwas.domain.landmark.Landmark;
import com.pns.albangwas.domain.landmark.LandmarkState;
import com.pns.albangwas.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LandmarkService {

    private final LandmarkRepository landmarkRepository;

    public List<Landmark> getAllLandmarks() {
        return landmarkRepository.findAllByStateEquals(LandmarkState.ACTIVE);
    }

    @Transactional
    public Landmark saveLandmark(Landmark landmark) {
        landmarkRepository.save(landmark);
        return landmark;
    }

    @Transactional
    public Landmark applyLandmark(Landmark landmark) {
        landmarkRepository.save(landmark);
        return landmark;
    }
}
