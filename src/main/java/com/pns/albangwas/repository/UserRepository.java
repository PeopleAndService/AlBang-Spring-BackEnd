package com.pns.albangwas.repository;

import com.pns.albangwas.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

      Optional<User> findByGoogleId(String googleId);
      Optional<User> findByNickname(String nickname);

//    private final EntityManager em;
//
//    public void save(User user) {
//        em.persist(user);
//    }
//
//    public User findOne(Long id) {
//        return em.find(User.class, id);
//    }
//
//    public List<User> findByGoogleId(String gid) {
//        List<User> users = em.createQuery("select u from User u where u.googleId = :googleId", User.class)
//                .setParameter("googleId", gid)
//                .getResultList();
//
//        return users;
//    }
}
