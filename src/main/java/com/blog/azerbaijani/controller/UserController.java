package com.blog.azerbaijani.controller;

import com.blog.azerbaijani.entity.*;
import com.blog.azerbaijani.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/registerChoose")
    public String getRegisterChoose() {
        return "registerChoose";
    }

    @GetMapping("/teacherRegister")
    public String teacherRegister(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherRegistrationPage";
    }

    @GetMapping("/studentRegister")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentRegistrationPage";
    }

    @PostMapping("/registerStudent")
    public String doStudentRegister(@ModelAttribute Student student, Model model) {
        for (Student s : studentRepository.findAll()) {
            if (s.getUsername().equals(student.getUsername())) {
                model.addAttribute("usernameError", "Username already exists");
                return "studentRegistrationPage";
            } else if (s.getEmail().equals(student.getEmail())) {
                model.addAttribute("emailError", "Email already exists");
                return "studentRegistrationPage";
            }
        }

        List<Subject> subjectList = new ArrayList<>();
        List<Topic> topics = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();

        List<Answer> answersForFirstQuestion = new ArrayList<>();
        Answer answer = new Answer("Thrust", false, null);
        Answer answer1 = new Answer("Gravity", false, null);
        Answer answer2 = new Answer("Lift", true, null);
        answersForFirstQuestion.add(answer);
        answersForFirstQuestion.add(answer1);
        answersForFirstQuestion.add(answer2);

        Question question = new Question("Which force allows a drone to rise into the air?", answersForFirstQuestion, null);
        questionList.add(question);
        answer.setQuestion(question);
        answer1.setQuestion(question);
        answer2.setQuestion(question);

        List<Answer> answersForSecondQuestion = new ArrayList<>();
        answersForSecondQuestion.add(new Answer("Generate lift", false, null));
        answersForSecondQuestion.add(new Answer("Control altitude", false, null));
        Answer answer3 = new Answer("Provide thrust", true, null);
        answersForSecondQuestion.add(answer3);

        Question secondQuestion = new Question("What is the primary function of the propellers on a drone?", answersForSecondQuestion, null);
        questionList.add(secondQuestion);
        answer3.setQuestion(secondQuestion);

        topics.add(new Topic("Drones", questionList, null));
        Subject subject = new Subject("STEAM", topics, new Symbol(null, null), null);
        subjectList.add(subject);

        student.setSubjectList(subjectList);

        studentRepository.save(student);

        return "redirect:studentRegister?success";
    }


    @PostMapping("/registerTeacher")
    public String doTeacherRegister(@ModelAttribute Teacher teacher, Model model){
        for(Teacher t : teacherRepository.findAll()){
            if(t.getUsername().equals(teacher.getUsername())){
                model.addAttribute("usernameError", "Username already exists");
                return "teacherRegistrationPage";
            }else if(t.getEmail().equals(teacher.getEmail())){
                model.addAttribute("emailError", "Email already exists");
                return "teacherRegistrationPage";
            }
        }
        teacherRepository.save(teacher);
        return "redirect:teacherRegister?success";
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

}
