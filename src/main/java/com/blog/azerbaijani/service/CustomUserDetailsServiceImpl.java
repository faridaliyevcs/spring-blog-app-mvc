package com.blog.azerbaijani.service;

import com.blog.azerbaijani.entity.Student;
import com.blog.azerbaijani.entity.Teacher;
import com.blog.azerbaijani.repository.StudentRepository;
import com.blog.azerbaijani.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        Teacher teacher = teacherRepository.findByUsername(username);

        if (student != null) {
            return buildUserDetails(student.getUsername(), student.getPassword(), "USER", "ROLE_USER");
        } else if (teacher != null) {
            return buildUserDetails(teacher.getUsername(), teacher.getPassword(), "TEACHER", "ROLE_TEACHER");
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    private UserDetails buildUserDetails(String username, String password, String... authorities) {
        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(username);

        builder.disabled(false);
        builder.password(password);
        builder.authorities(authorities);

        return builder.build();
    }
}
