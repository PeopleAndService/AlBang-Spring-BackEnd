package com.pns.albangwas.domain.landmark;

import com.pns.albangwas.domain.guestbook.GuestBook;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Coordinate coordinate;

    @Enumerated(EnumType.STRING)
    private LandmarkState state;

    private String imageName;

    @OneToMany(mappedBy = "landmark")
    private List<GuestBook> guestBooks = new ArrayList<>();

    @Builder
    public Landmark(String name, Coordinate coordinate, LandmarkState state, String imageUrl) {
        this.name = name;
        this.coordinate = coordinate;
        this.state = state;
        this.imageName = imageUrl;
    }
}
