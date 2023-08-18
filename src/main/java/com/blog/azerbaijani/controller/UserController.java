package com.blog.azerbaijani.controller;

import com.blog.azerbaijani.entity.Document;
import com.blog.azerbaijani.entity.Like;
import com.blog.azerbaijani.entity.User;
import com.blog.azerbaijani.entity.Comment;
import com.blog.azerbaijani.repository.LikeRepository;
import com.blog.azerbaijani.repository.DocumentRepository;
import com.blog.azerbaijani.repository.UserRepository;
import com.blog.azerbaijani.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @GetMapping("/register")
    public String registrationPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model){
        for(User u : userRepository.findAll()){
            if(u.getUsername().equals(user.getUsername())){
                model.addAttribute("usernameError", "Username already exists");
                return "register";
            }else if(u.getEmail().equals(user.getEmail())){
                model.addAttribute("emailError", "Email already exists");
                return "register";
            }
        }
        userRepository.save(user);
        return "redirect:register?success";
    }

    @GetMapping("/login")
    public String loginPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("alreadyLoggedIn", false);
        } else {
            model.addAttribute("alreadyLoggedIn", true);
        }
        return "login";
    }
    @GetMapping("/search")
    public String searchPage(@RequestParam(name="category")String request,
                             Model model){
        List<Document> documents = new ArrayList<>();
        for(Document document : documentRepository.findAll()){
            if(document.getDocumentType().equals(request)){
                documents.add(document);
            }
        }
        model.addAttribute("documents", documents);
        return "search";
    }

}






