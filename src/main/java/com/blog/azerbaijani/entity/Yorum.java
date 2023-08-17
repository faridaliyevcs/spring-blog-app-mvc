package com.blog.azerbaijani.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Yorum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String icerik;

    @ManyToOne
    @JoinColumn(name = "makale_id")
    private Makale makale;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
