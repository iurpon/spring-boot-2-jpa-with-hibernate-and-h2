package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(128)", unique = true)
    private String name;

    @Column(name = "photos")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//            (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Photo> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photos=" + photos +
                '}';
    }
}
