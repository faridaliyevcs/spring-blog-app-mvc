package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Document;
import com.blog.azerbaijani.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByDocument(Document document);
}
