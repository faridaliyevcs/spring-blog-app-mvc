package com.blog.azerbaijani.controller;

import com.blog.azerbaijani.entity.Comment;
import com.blog.azerbaijani.entity.Document;
import com.blog.azerbaijani.entity.Like;
import com.blog.azerbaijani.entity.User;
import com.blog.azerbaijani.repository.CommentRepository;
import com.blog.azerbaijani.repository.DocumentRepository;
import com.blog.azerbaijani.repository.LikeRepository;
import com.blog.azerbaijani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InteractionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping("/add-comment")
    public String documentInteractionComment(@RequestParam(name = "comment")String comment,
                                             @RequestParam(name = "document_id")Integer document_id,
                                             Model model) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(authentication);
        Comment comment1 = new Comment();
        comment1.setContent(comment);
        comment1.setUser(user);
        comment1.setDocument(documentRepository.getById(document_id));
        commentRepository.save(comment1);
        model.addAttribute("document", documentRepository.getById(document_id));
        return "document_interaction";
    }

    @PostMapping("/like")
    public String documentInteractionLike(@RequestParam(name = "document_id")String documentId,
                                          Model model) {

        int doc_id = Integer.parseInt(documentId);
        Document document = documentRepository.getById(doc_id);
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(authentication);
        boolean alreadyLiked = false;
        for (Like like : document.getLikes()) {
            if (like.getUser().getUsername().equals(user.getUsername())) {
                alreadyLiked = true;
                break;
            }
        }

        if (!alreadyLiked) {
            int a;
            if(document.getLikeCount()==null){
                a=0;
            }else{
                a= document.getLikeCount();
            }
            a++;
            Like like = new Like();
            like.setUser(user);
            like.setDocument(document);

            document.getLikes().add(like);
            document.setLikeCount(a);

            likeRepository.save(like);
            documentRepository.save(document);
        }

        model.addAttribute("document", document);

        return "document_interaction";
    }


}
