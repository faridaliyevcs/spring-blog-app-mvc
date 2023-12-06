package com.blog.azerbaijani.controller;

import com.blog.azerbaijani.entity.*;
import com.blog.azerbaijani.repository.StudentRepository;
import com.blog.azerbaijani.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/subjects")
    @Transactional
    public String getSubjects(Model model) {
//      String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
//      Student user = studentRepository.findByUsername(authentication);
        Student student = studentRepository.getById(6);
        model.addAttribute("subjects", student.getSubjectList());
        return "subjects";
    }

    @GetMapping("/subjects/{id}")
    public String getTopics(Model model, @PathVariable Integer id) {
        Subject subject = subjectRepository.getById(id);
        model.addAttribute("subject", subject);
        model.addAttribute("topics", subject.getTopics());
        return "topics";
    }

    @GetMapping("subjects/{id}/{topic_id}")
    public String getTopic(@PathVariable(name = "id") Integer subject_id,
                           @PathVariable(name = "topic_id") Integer topic_id,
                           Model model) {
        Subject subject = subjectRepository.getById(subject_id);
        Topic topic = subject.getTopics().get(topic_id - 1);
        model.addAttribute("subject", subject);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @PostMapping("subjects/{id}/{topic_id}")
    public String answerQuestions(
            @PathVariable(name = "topic_id") Integer topic_id,
            @PathVariable(name = "id") Integer subject_id,
            Model model) {

//        List<Answer> answerList = new ArrayList<>();
//
//        for (Map.Entry<String, String> entry : formParams.entrySet()) {
//            if (entry.getKey().startsWith("answerList[")) {
//                Integer answerId = Integer.valueOf(entry.getValue());
//                Answer answer = new Answer(answerId);
//                answerList.add(answer);
//            }
//        }
//
//        int check = 0;
//        for (Answer a : answerList) {
//            if (a.getBool() != null && a.getBool()) {
//                check++;
//            }
//        }
//        if (check == 2) {
//            int a = subjectRepository.getById(subject_id).getSymbol().getCircumstance();
//            a++;
//            subjectRepository.getById(subject_id).getSymbol().setCircumstance(a);
//        }
        Symbol symbol = new Symbol(subjectRepository.getById(subject_id), 2);
        subjectRepository.getById(subject_id).setSymbol(symbol);
        model.addAttribute("subject", subjectRepository.getById(subject_id));
        model.addAttribute("topics", subjectRepository.getById(subject_id).getTopics());
        return "topics";
    }

}
