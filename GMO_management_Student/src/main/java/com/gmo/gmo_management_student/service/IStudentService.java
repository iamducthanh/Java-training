package com.gmo.gmo_management_student.service;

import com.gmo.gmo_management_student.entities.StudentEntity;

import java.util.Date;
import java.util.List;

/**
 * Tên dự án: GMO_management_student
 * Tên class IStudentService.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */
public interface IStudentService {
    /**
     * @return danh sách sinh viên
     * mô tả: lấy ra danh sách sinh viên
     */
    List<StudentEntity> findAllStudent();
    void saveStudents(List<StudentEntity> studentEntities);
    StudentEntity findStudentByFullnameAndDateOfBirth(String fullname, Date dateOfBirth);
}
