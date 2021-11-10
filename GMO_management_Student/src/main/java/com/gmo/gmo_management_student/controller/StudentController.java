package com.gmo.gmo_management_student.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmo.gmo_management_student.dto.ErrorMessageDto;
import com.gmo.gmo_management_student.dto.StudentDto;
import com.gmo.gmo_management_student.dto.ValidList;
import com.gmo.gmo_management_student.entities.ClassEntity;
import com.gmo.gmo_management_student.entities.StudentEntity;
import com.gmo.gmo_management_student.service.IClassService;
import com.gmo.gmo_management_student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private ObjectError objectError;

    @Autowired
    private IClassService classService;
    @Autowired
    private IStudentService studentService;


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
    public List<ErrorMessageDto> addStudent(@RequestBody @Valid ValidList<StudentDto> studentDtos, BindingResult result) throws JsonProcessingException, ParseException {
        ResourceBundle rb = ResourceBundle.getBundle("message/message_add_student");
        List<ErrorMessageDto> errorMessageDtos = new ArrayList<>();

        if (result.hasErrors()) {
            List<ObjectError> objectErrors = result.getAllErrors();
            for (ObjectError objectError : objectErrors) {
                String messageError = objectError.getDefaultMessage();
                messageError.replace("-","_");
                String codes = objectError.getCodes()[0];
//                System.out.println(codes);
                String field = codes.substring(codes.lastIndexOf('.')+1, codes.length());
                String index = codes.substring(codes.indexOf('[')+1, codes.lastIndexOf(']'));
//                System.out.println(messageError);
//                System.out.println(field);
//                System.out.println(index);

                String messageTemplate = rb.getString(messageError);
                String message = "";
                message = rb.getString(messageError.replace("{1}", ""));

                if(field.equals("classId")){
                    errorMessageDtos.add(new ErrorMessageDto(Integer.parseInt(index), 5, rb.getString(messageError.replace("{1}", "Class")), "Class"));
                }
                if(field.equals("dateOfBirth")){
                    errorMessageDtos.add(new ErrorMessageDto(Integer.parseInt(index), 9, rb.getString(messageError.replace("{1}", "Date of birth")), "Date of birth"));
                }
                if(field.equals("fullname")){
                    errorMessageDtos.add(new ErrorMessageDto(Integer.parseInt(index), 7, rb.getString(messageError.replace("{1}", "Full name")), "Full name"));
                }
                if(field.equals("phone")){
                    errorMessageDtos.add(new ErrorMessageDto(Integer.parseInt(index), 13, rb.getString(messageError.replace("{1}", "Phone")), "Phone"));
                }
                if(field.equals("sex")){
                    errorMessageDtos.add(new ErrorMessageDto(Integer.parseInt(index), 11, rb.getString(messageError.replace("{1}", "Sex")), "Sex"));
                }

//                if(!errors.contains(message)){
//                    errors.add(message);
//                }
            }
        }

        for(int i=0;i<studentDtos.size();i++){
            if(!studentDtos.get(i).getClassId().isBlank()){
                ClassEntity classEntity = classService.findClassById(Integer.parseInt(studentDtos.get(i).getClassId()));
                if(classEntity == null){
                    errorMessageDtos.add(new ErrorMessageDto(studentDtos.get(i).getRowId(), 5, rb.getString("C-00-001"), "Class"));
                }
            }
            for(int j=0;j<studentDtos.size();j++){
                if(i==j){
                    continue;
                }
                if(studentDtos.get(i).getFullname().equals(studentDtos.get(j).getFullname())
                && studentDtos.get(i).getDateOfBirth().equals(studentDtos.get(j).getDateOfBirth())){
                    System.out.println(studentDtos.get(i).toString());
                    errorMessageDtos.add(new ErrorMessageDto(studentDtos.get(i).getRowId(), 9, rb.getString("C-00-008"), "Date of birth"));
                    errorMessageDtos.add(new ErrorMessageDto(studentDtos.get(i).getRowId(), 7, rb.getString("C-00-008"), "Sex"));

                }
            }
        }

        if(errorMessageDtos.size() == 0){
            List<StudentEntity> studentEntities = new ArrayList<>();
            for(int i=0;i<studentDtos.size();i++){
                ClassEntity classEntity = new ClassEntity();
                classEntity.setId(Integer.parseInt(studentDtos.get(i).getClassId()));
                studentEntities.add(new StudentEntity(
                    studentDtos.get(i).getFullname(),
                        studentDtos.get(i).getDateOfBirthDate(),
                        studentDtos.get(i).getSex().equals("1") ? true : false,
                        studentDtos.get(i).getPhone(),
                        studentDtos.get(i).getNote(),
                        classEntity
                ));
            }
            studentService.saveStudents(studentEntities);
            studentEntities.forEach((o) -> {
                System.out.println(o.toString());
            });
        }



        return errorMessageDtos;
    }

//    public boolean checkStudentConstant(){
//
//    }
}
