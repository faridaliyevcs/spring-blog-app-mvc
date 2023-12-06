package com.blog.azerbaijani.controller;

import com.blog.azerbaijani.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @GetMapping("/teacher")
    public String teacherPage(){
        return "teacherPage";
    }

}
