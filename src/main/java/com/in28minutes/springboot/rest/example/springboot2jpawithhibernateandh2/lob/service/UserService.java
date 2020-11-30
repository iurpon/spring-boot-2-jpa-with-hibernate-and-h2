package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.service;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserByName(String userName);

    List<User> getAll();

    void createOrUpdate(User user);
}
