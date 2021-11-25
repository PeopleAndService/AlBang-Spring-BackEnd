package com.pns.albangwas.domain.landmark;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter @Setter
@Entity
public class LandmarkImage {

    @Id
    @GeneratedValue
    @Column(name = "landmark_image_id")
    private Long id;

    private String imageURL;

    @OneToOne(mappedBy = "landmarkImage", fetch = LAZY)
    private Landmark landmark;
}
