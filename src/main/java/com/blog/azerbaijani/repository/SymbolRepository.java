package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Answer;
import com.blog.azerbaijani.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Integer> {

}
