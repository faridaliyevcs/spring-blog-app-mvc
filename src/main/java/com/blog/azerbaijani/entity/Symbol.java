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
public class Symbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "subject")
    private Subject subject;
    @Column
    private Integer circumstance;
    public Symbol(Subject subject, Integer circumstance) {
        this.subject = subject;
        this.circumstance = circumstance;
    }

}
