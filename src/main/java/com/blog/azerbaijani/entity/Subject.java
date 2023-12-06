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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Topic> topics;

    @OneToOne(mappedBy = "subject")
    private Symbol symbol;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    public Subject(String name, List<Topic> topics) {
        this.name = name;
        this.topics = topics;
    }

    public Subject(String name, List<Topic> topics, Symbol symbol, Student student) {
        this.name = name;
        this.topics = topics;
        this.symbol = symbol;
        this.student = student;
    }
}
