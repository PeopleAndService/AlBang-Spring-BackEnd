package com.pns.albangwas.domain.user;

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
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String googleId;

    private String email;

    private String name;

    @Column(unique = true)
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<GuestBook> guestBooks = new ArrayList<>();

    @Builder
    public User(String googleId, String email, String name) {
        this.googleId = googleId;
        this.email = email;
        this.name = name;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
