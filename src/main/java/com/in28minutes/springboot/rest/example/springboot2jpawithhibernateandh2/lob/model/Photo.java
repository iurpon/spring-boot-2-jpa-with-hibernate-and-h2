package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "book", columnDefinition="CLOB")
    private char[] book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true, insertable = true, updatable = true)
    private User user;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char[] getBook() {
        return book;
    }

    public void setBook(char[] book) {
        this.book = book;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", book='" + (book != null) + '\'' +
                ", photo=" + (photo != null) +
                '}';
    }
}
