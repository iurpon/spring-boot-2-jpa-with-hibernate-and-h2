package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.repository;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.Photo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PhotoRepositoryImpl implements PhotoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Photo> getPhotoByUserId(Long id) {
        return entityManager.createNativeQuery("select * from photo  where user_id = :id", Photo.class)
                .setParameter("id", id)
                .getResultList();

    }
}
