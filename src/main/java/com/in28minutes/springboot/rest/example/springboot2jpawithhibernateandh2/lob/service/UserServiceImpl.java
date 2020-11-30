package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.service;


import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void createOrUpdate(User user) {
         userRepository.createOrUpdate(user);
    }
}
