package com.pns.albangwas.api.dto.landmark;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class LandmarkGetResponseDto {

    private int count;
    private List<LandmarkNormalResponseDto> results;

    public LandmarkGetResponseDto(List<LandmarkNormalResponseDto> results) {
        this.count = results.size();
        this.results = results;
    }
}
