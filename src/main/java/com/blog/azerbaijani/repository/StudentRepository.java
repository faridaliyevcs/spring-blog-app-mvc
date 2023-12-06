package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmailAndPassword(String email, String password);

    Student findByEmail(String email);

    Student findByUsername(String username);

}
