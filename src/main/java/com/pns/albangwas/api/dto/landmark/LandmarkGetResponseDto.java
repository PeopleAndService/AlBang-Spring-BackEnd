package com.pns.albangwas.api.dto.landmark;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class LandmarkGetResponseDto {

    private int count;
    private List<LandmarkNormalResponseDto> result;

    public LandmarkGetResponseDto(List<LandmarkNormalResponseDto> result) {
        this.count = result.size();
        this.result = result;
    }
}
