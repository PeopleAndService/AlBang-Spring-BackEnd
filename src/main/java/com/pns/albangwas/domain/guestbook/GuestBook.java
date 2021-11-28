package com.pns.albangwas.domain.guestbook;

import com.pns.albangwas.domain.BaseTimeEntity;
import com.pns.albangwas.domain.landmark.Landmark;
import com.pns.albangwas.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Entity
public class GuestBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String anchor;

    @Enumerated(value = EnumType.STRING)
    private GuestBookState state;

    @OneToMany(mappedBy = "guestBook")
    private List<GuestBookVan> guestBookVans = new ArrayList<>();

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    @Builder
    public GuestBook(String content, String anchor, GuestBookState state) {
        this.content = content;
        this.anchor = anchor;
        this.state = state;
    }

    public void setUser(User user) {
        this.user = user;
        user.getGuestBooks().add(this);
    }

    public void setLandmark(Landmark landmark) {
        this.landmark = landmark;
        landmark.getGuestBooks().add(this);
    }
}
