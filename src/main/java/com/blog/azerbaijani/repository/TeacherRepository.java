package com.blog.azerbaijani.repository;

import com.blog.azerbaijani.entity.Student;
import com.blog.azerbaijani.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    Teacher findByEmailAndPassword(String email, String password);

    Teacher findByEmail(String email);

    Teacher findByUsername(String username);


}
