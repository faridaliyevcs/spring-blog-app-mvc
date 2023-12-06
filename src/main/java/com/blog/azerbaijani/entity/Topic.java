package com.blog.azerbaijani.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Question> questionList;

    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    public Topic(String name, List<Question> questionList, Subject subject) {
        this.name = name;
        this.questionList = questionList;
        this.subject = subject;
    }

}
