package com.gmo.gmo_management_student.controller;

import com.gmo.gmo_management_student.entities.ClassEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Tên dự án: GMO_management_student
 * Tên class ClassController.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */
@Controller
public class ClassController {
    @GetMapping("/class/{id}")
    public boolean getClassById(){
        return true;
    }
}
