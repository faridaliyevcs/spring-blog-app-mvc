package com.blog.azerbaijani.service;

import com.blog.azerbaijani.entity.User;
import com.blog.azerbaijani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> login(String email, String password){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));
        return user;
    }
}
