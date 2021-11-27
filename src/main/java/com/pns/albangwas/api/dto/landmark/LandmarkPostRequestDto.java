package com.pns.albangwas.api.dto.landmark;

import com.pns.albangwas.domain.landmark.Coordinate;
import com.pns.albangwas.domain.landmark.Landmark;
import com.pns.albangwas.domain.landmark.LandmarkState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LandmarkPostRequestDto {

    private String name;
    private String latitude;
    private String longitude;

    @Builder
    public LandmarkPostRequestDto(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Landmark toEntity(String imageUrl, LandmarkState state) {
        return Landmark.builder()
                .name(this.name)
                .coordinate(new Coordinate(this.latitude, this.longitude))
                .state(state)
                .imageUrl(imageUrl)
                .build();
    }
}
