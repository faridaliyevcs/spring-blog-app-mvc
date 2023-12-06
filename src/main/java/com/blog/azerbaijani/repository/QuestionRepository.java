package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Answer;
import com.blog.azerbaijani.entity.Question;
import com.blog.azerbaijani.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
