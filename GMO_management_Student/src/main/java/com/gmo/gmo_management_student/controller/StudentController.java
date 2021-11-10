package com.gmo.gmo_management_student.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmo.gmo_management_student.dto.ErrorMessageDto;
import com.gmo.gmo_management_student.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Tên dự án: GMO_management_student
 * Tên class StudentController.java
 * Version     date            by              change/comment
 * 1.0         10/11/2021      GMO_ThanhND     create
 */

@Controller
public class StudentController {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * mô tả: hiển thị giao diện danh sách sinh viên
     * @return trang hiển thị danh sách sinh viên
     */
    @GetMapping("/")
    public String listStudentPage(){
        return "views/liststudent";
    }

    /**
     * mô tả: hiển thị giao diện thêm sinh viên
     * @return trang hiển thị thêmsinh viên
     */
    @GetMapping("/add-student")
    public String addStudentPage(){
        return "views/addstudent";
    }

    /**
     * Mô tả: Nhận danh sách sinh viên đăng kí để xử lí và trả về lỗi
     * @param studentDtos danh sách sinh viên dạng json
     * @return danh sách lỗi
     * @throws JsonProcessingException
     */
    @PostMapping("/add-student")
    @ResponseBody
    public List<ErrorMessageDto> addStudent(@RequestBody @Valid List<StudentDto> studentDtos, BindingResult result) throws JsonProcessingException {
        studentDtos.forEach((o1) -> {
            System.out.println(o1.toString());
        });
        System.out.println(result.hasErrors());
        System.out.println(result.getAllErrors().size());
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError objectError : errors) {
                String messageError = objectError.getDefaultMessage();
                System.out.println(
                        messageError
                );
            }
        }
        List<ErrorMessageDto> errorMessageDtos = new ArrayList<>();
        return errorMessageDtos;
    }
}
