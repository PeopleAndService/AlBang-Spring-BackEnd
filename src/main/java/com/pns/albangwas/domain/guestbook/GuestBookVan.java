package com.pns.albangwas.domain.guestbook;

import com.pns.albangwas.domain.user.User;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class GuestBookVan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "guestbook_id")
    private GuestBook guestBook;

    public void setUser(User user) {
        this.user = user;
        user.getGuestBookVans().add(this);
    }

    public void setGuestBook(GuestBook guestBook) {
        this.guestBook = guestBook;
        guestBook.getGuestBookVans().add(this);
    }
}
