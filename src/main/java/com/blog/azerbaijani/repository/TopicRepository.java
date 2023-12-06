package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Answer;
import com.blog.azerbaijani.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
