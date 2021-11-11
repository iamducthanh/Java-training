package com.gmo.gmo_management_student.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmo.gmo_management_student.constant.FieldColumn;
import com.gmo.gmo_management_student.constant.FieldName;
import com.gmo.gmo_management_student.constant.Message;
import com.gmo.gmo_management_student.constant.ParamMessage;
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
import java.text.SimpleDateFormat;
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
     *
     * @return trang hiển thị danh sách sinh viên
     */
    @GetMapping("/")
    public String listStudentPage() {
        return "views/liststudent";
    }

    /**
     * mô tả: hiển thị giao diện thêm sinh viên
     *
     * @return trang hiển thị thêmsinh viên
     */
    @GetMapping("/add-student")
    public String addStudentPage() throws ParseException {
        return "views/addstudent";
    }

    /**
     * Mô tả: Nhận danh sách sinh viên đăng kí để xử lí và trả về lỗi
     *
     * @param studentDtos danh sách sinh viên dạng json
     * @param result      danh sách lỗi
     * @return danh sách lỗi
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @PostMapping("/add-student")
    @ResponseBody
    public List<ErrorMessageDto> addStudent(@RequestBody @Valid ValidList<StudentDto> studentDtos, BindingResult result) throws JsonProcessingException, ParseException {
        List<ErrorMessageDto> errorMessageDtos = new ArrayList<>();
        getAllError(result, studentDtos, errorMessageDtos);
        checkStudents(studentDtos, errorMessageDtos);

        // nếu không phát hiện lỗi gì
        if (errorMessageDtos.size() == 0) {
            addListStudent(studentDtos);
        }
        return errorMessageDtos;
    }

    /**
     * mô tả: đưa các lỗi vào danh sách để gửi về phía máy khách
     *
     * @param errorMessageDtos danh sách để chứa lỗi
     * @param index            vị trí của dòng có lõi
     * @param column           vị trí của cột có lỗi
     * @param messageError     mã lỗi
     * @param param            tham số của thông báo
     */
    public void addErrorMessage(List<ErrorMessageDto> errorMessageDtos, Integer index, Integer column, String messageError, String param) {
        ResourceBundle rb = ResourceBundle.getBundle("message/message_add_student");
        errorMessageDtos.add(new ErrorMessageDto(index, column, rb.getString(messageError.replace("{1}", param)), param));
    }

    /**
     * mô tả: đưa danh sách sinh viên vào database
     *
     * @param studentDtos danh sách đưa vào
     * @throws ParseException
     */
    public void addListStudent(List<StudentDto> studentDtos) throws ParseException {
        List<StudentEntity> studentEntities = new ArrayList<>();
        for (int i = 0; i < studentDtos.size(); i++) {
            // tạo 1 đối tượng class tương ứng
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
    }

    /**
     * mô tả: lấy danh sách lỗi đã được kiểm tra qua @Valid
     *
     * @param result           danh sách lõi đã được kiểm tra
     * @param studentDtos      danh sách sinh viên
     * @param errorMessageDtos danh sách lỗi
     */
    public void getAllError(BindingResult result, List<StudentDto> studentDtos, List<ErrorMessageDto> errorMessageDtos) {
        if (result.hasErrors()) {
            List<ObjectError> objectErrors = result.getAllErrors();
            for (ObjectError objectError : objectErrors) {
                // lấy mã lỗi được ném ra
                String messageError = objectError.getDefaultMessage();

                // đổi kí tự để giống với cấu hình bên hiển thị
                messageError.replace("-", "_");

                // lấy mã lỗi đầy đủ
                String codes = objectError.getCodes()[0];

                // lấy thuộc tính bị lỗi
                String field = codes.substring(codes.lastIndexOf('.') + 1, codes.length());

                // lấy vị trí của thuộc tính bị lỗi trong danh sách
                String index = codes.substring(codes.indexOf('[') + 1, codes.lastIndexOf(']'));

                // lấy dòng bị lỗi trong bảng
                Integer row = studentDtos.get(Integer.parseInt(index)).getRowId();

                // kiểm tra và thêm thông báo lỗi tương ứng với các ô trong bảng
                if (field.equals(FieldName.CLASS_ID)) {
                    addErrorMessage(errorMessageDtos, row, FieldColumn.CLASS_COL, messageError, ParamMessage.CLASS_FIELD);
                }
                if (field.equals(FieldName.DATE_OF_BIRTH)) {
                    addErrorMessage(errorMessageDtos, row, FieldColumn.DATE_OF_BIRTH_COL, messageError, ParamMessage.DATE_OF_BIRTH_FIELD);
                }
                if (field.equals(FieldName.FULL_NAME)) {
                    addErrorMessage(errorMessageDtos, row, FieldColumn.FULLNAME_COL, messageError, ParamMessage.FULLNAME_FIELD);
                }
                if (field.equals(FieldName.PHONE)) {
                    addErrorMessage(errorMessageDtos, row, FieldColumn.PHONE_COL, messageError, ParamMessage.PHONE_FIELD);
                }
                if (field.equals(FieldName.SEX)) {
                    addErrorMessage(errorMessageDtos, row, FieldColumn.SEX_COL, messageError, ParamMessage.SEX_FIELD);
                }
            }
        }
    }


    /**
     * mô tả: kiểm tra các lỗi còn lại
     *
     * @param studentDtos      danh sách sinh viên
     * @param errorMessageDtos danh sách lỗi
     */
    public void checkStudents(List<StudentDto> studentDtos, List<ErrorMessageDto> errorMessageDtos) throws ParseException {
        String regexDate = "\\d{4}-\\d{2}-\\d{2}"; // format yyyy-MM-dd
        for (int i = 0; i < studentDtos.size(); i++) {
            // kiểm tra class có tồn tại trong database
            if (!studentDtos.get(i).getClassId().isBlank()) {
                ClassEntity classEntity = classService.findClassById(Integer.parseInt(studentDtos.get(i).getClassId()));
                if (classEntity == null) {
                    addErrorMessage(errorMessageDtos, studentDtos.get(i).getRowId(), FieldColumn.CLASS_COL, Message.CLASS_NOT_EXIST, ParamMessage.CLASS_FIELD);
                }
            }
            for (int j = 0; j < studentDtos.size(); j++) {
                // nếu fullname không để trống và dateOfBirth đúng định dạng
                if (!studentDtos.get(i).getFullname().isBlank()
                        && studentDtos.get(i).getDateOfBirth().matches(regexDate)) {
                    // kiểm tra dòng hiên tại với database
                    if (i == j) {
                        // kiểm tra sinh viên có tồn tại trong database không
                        StudentEntity studentEntity = studentService.findStudentByFullnameAndDateOfBirth(studentDtos.get(i).getFullname(), studentDtos.get(i).getDateOfBirthDate());
                        if (studentEntity != null) {
                            addErrorMessage(errorMessageDtos, studentDtos.get(i).getRowId(), FieldColumn.DATE_OF_BIRTH_COL, Message.STUDENT_EXISTS, ParamMessage.DATE_OF_BIRTH_FIELD);
                            addErrorMessage(errorMessageDtos, studentDtos.get(i).getRowId(), FieldColumn.FULLNAME_COL, Message.STUDENT_EXISTS, ParamMessage.SEX_FIELD);
                        }
                        continue;
                    }
                    // kiểm tra dòng hiện tại với các dòng khác trong bảng
                    if (studentDtos.get(i).getFullname().equals(studentDtos.get(j).getFullname())
                            && studentDtos.get(i).getDateOfBirth().equals(studentDtos.get(j).getDateOfBirth())) {
                        addErrorMessage(errorMessageDtos, studentDtos.get(i).getRowId(), FieldColumn.DATE_OF_BIRTH_COL, Message.STUDENT_EXISTS, ParamMessage.DATE_OF_BIRTH_FIELD);
                        addErrorMessage(errorMessageDtos, studentDtos.get(i).getRowId(), FieldColumn.FULLNAME_COL, Message.STUDENT_EXISTS, ParamMessage.SEX_FIELD);
                    }
                }
            }
        }
    }

}
