package com.pns.albangwas.domain.guestbook;

import com.pns.albangwas.domain.BaseTimeEntity;
import com.pns.albangwas.domain.landmark.Landmark;
import com.pns.albangwas.domain.user.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class GuestBook extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "guestbook_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String anchor;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    public void setUser(User user) {
        this.user = user;
        user.getGuestBooks().add(this);
    }

    public void setLandmark(Landmark landmark) {
        this.landmark = landmark;
        landmark.getGuestBooks().add(this);
    }
}
