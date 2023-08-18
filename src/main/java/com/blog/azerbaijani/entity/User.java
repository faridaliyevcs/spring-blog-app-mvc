package com.blog.azerbaijani.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Document> documents;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
