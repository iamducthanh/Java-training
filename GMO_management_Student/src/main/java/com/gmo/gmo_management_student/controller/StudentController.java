package com.gmo.gmo_management_student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @GetMapping("/")
    public String listStudentPage(){
        return "views/liststudent";
    }

    @GetMapping("/add-student")
    public String addStudentPage(){
        return "views/addstudent";
    }
}
