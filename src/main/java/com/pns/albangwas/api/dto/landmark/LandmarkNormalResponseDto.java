package com.pns.albangwas.api.dto.landmark;

import com.pns.albangwas.domain.landmark.Landmark;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LandmarkNormalResponseDto {

    private Long id;
    private String name;
    private String imageUrl;
    private String latitude;
    private String longitude;
    private String status;

    public LandmarkNormalResponseDto(Landmark landmark) {
        this.id = landmark.getId();
        this.name = landmark.getName();
        this.imageUrl = landmark.getImageUrl();
        this.latitude = landmark.getCoordinate().getLatitude();
        this.longitude = landmark.getCoordinate().getLongitude();
        this.status = landmark.getState().name();
    }
}
