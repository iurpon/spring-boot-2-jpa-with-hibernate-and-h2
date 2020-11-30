package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.repository;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User getUserByName(String name);

    List<User> getAll();

    void createOrUpdate(User user);

    User getUserWithPhoto(String name);
}


