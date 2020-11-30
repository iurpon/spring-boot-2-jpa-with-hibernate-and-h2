package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.repository;


import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList().stream().findFirst().get();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void createOrUpdate(User user) {
        System.out.println("trying to save " + user);
        entityManager.merge(user);
    }

    @Override
    public User getUserWithPhoto(String name) {
        return entityManager.createQuery("select u from User u, Photo ph where u.name = :name and u.id = ph.user_id", User.class)
                .setParameter("name", name)
                .getResultList().stream().findFirst().get();
    }
}
