package com.pns.albangwas.domain.landmark;

import com.pns.albangwas.domain.guestbook.GuestBook;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class Landmark {

    @Id
    @GeneratedValue
    @Column(name = "landmark_id")
    private Long id;

    private String name;

    @Embedded
    private Coordinate coordinate;

    @Enumerated(EnumType.STRING)
    private LandmarkState state;

    @OneToMany(mappedBy = "landmark")
    private List<GuestBook> guestBooks = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "landmark_image_id")
    private LandmarkImage landmarkImage;

    public void setImage(LandmarkImage landmarkImage) {
        this.landmarkImage = landmarkImage;
        landmarkImage.setLandmark(this);
    }
}
