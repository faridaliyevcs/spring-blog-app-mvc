package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Makale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakaleRepository extends JpaRepository<Makale,Integer> {
}
