package com.blog.azerbaijani.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String content;
    @Column
    private Boolean bool;
    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

    public Answer(String content, Boolean bool, Question question) {
        this.content = content;
        this.bool = bool;
        this.question = question;
    }
    public Answer(Integer id) {
        this.id = id;
    }
}
