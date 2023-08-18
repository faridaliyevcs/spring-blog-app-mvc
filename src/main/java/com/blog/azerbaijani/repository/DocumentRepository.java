package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer> {
}
