package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Makale;
import com.blog.azerbaijani.entity.Yorum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YorumRepository extends JpaRepository<Yorum,Integer> {
    List<Yorum> findByMakale(Makale makale);
}
