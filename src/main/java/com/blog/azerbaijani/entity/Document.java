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
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String header;

    @Column
    private String content;

    @Column(name = "document_type")
    private String documentType;

    @OneToMany(mappedBy = "document")
    private List<Comment> comments;

    @Column(name = "like_count")
    private Integer likeCount;

    @OneToMany(mappedBy = "document")
    private List<Like> likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
