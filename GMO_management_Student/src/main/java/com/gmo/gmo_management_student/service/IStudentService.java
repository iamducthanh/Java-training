package com.gmo.gmo_management_student.service;

import com.gmo.gmo_management_student.entities.StudentEntity;

import java.util.Date;
import java.util.List;

/**
 * Liệt kê các phương thức để thao tác với Student
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */
public interface IStudentService {
    /**
     * Lấy ra danh sách sinh viên
     * @return danh sách sinh viên
     */
    List<StudentEntity> findAllStudent();

    /**
     * Lưu danh sách sinh viên vào database
     * @param studentEntities danh sách thực thể sinh viên
     */
    void saveStudents(List<StudentEntity> studentEntities);

    /**
     * Lấy sinh viên theo họ tên và ngày sinh
     * @param fullname họ tên sinh viên
     * @param dateOfBirth ngày sinh
     * @return sinh viên được tìm thấy
     */
    StudentEntity findStudentByFullnameAndDateOfBirth(String fullname, Date dateOfBirth);
}
