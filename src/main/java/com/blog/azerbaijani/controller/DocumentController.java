package com.blog.azerbaijani.controller;

import com.blog.azerbaijani.entity.Comment;
import com.blog.azerbaijani.entity.Document;
import com.blog.azerbaijani.entity.User;
import com.blog.azerbaijani.repository.CommentRepository;
import com.blog.azerbaijani.repository.DocumentRepository;
import com.blog.azerbaijani.repository.LikeRepository;
import com.blog.azerbaijani.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class DocumentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @GetMapping("/document")
    public String getDocument(Model model){

        return "create_document";
    }
    @PostMapping("/document")
    public String createDocument(@RequestParam(name = "header")String header,
                                 @RequestParam(name = "content")String content,
                                 @RequestParam(name = "category") String type,
                                 Model model){
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(authentication);
        Document document = new Document();
        document.setDocumentType(type);
        document.setUser(user);
        document.setHeader(header);
        document.setContent(content);
        documentRepository.save(document);
        model.addAttribute("document", document);
        return "redirect:documents";
    }
    @GetMapping("/show-document/{id}")
    public String showDocument(Model model,
                               @PathVariable(name = "id") Integer document_id) {
        int a = document_id;
        model.addAttribute("document", documentRepository.getById(a));
        Document document = documentRepository.getById(a);
        List<Comment> comments = commentRepository.findByDocument(document);
        model.addAttribute(comments);
        model.addAttribute(document.getLikes());
        return "document_interaction";
    }
    @GetMapping("/documents")
    public String getAllTheDocument(Model model){
        List<Document> documents = documentRepository.findAll();
        model.addAttribute("documentList", documents);
        return "documents";
    }


}
