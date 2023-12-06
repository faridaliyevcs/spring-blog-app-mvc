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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;

    @ManyToOne
    @JoinColumn(name = "topic")
    private Topic topic;

    public Question(Topic topic) {
        this.topic = topic;
    }

    public Question(String content, List<Answer> answerList, Topic topic) {
        this.content = content;
        this.answerList = answerList;
        this.topic = topic;
    }
}
