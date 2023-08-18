package com.blog.azerbaijani.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
