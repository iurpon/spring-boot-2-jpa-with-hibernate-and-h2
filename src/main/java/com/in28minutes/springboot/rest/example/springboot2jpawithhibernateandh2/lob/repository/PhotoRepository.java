package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.repository;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.Photo;

import java.util.List;

public interface PhotoRepository {

    List<Photo> getPhotoByUserId(Long id);
}
