package com.gmo.gmo_management_student.service.impl;

import com.gmo.gmo_management_student.entities.StudentEntity;
import com.gmo.gmo_management_student.repository.StudentRepository;
import com.gmo.gmo_management_student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Tên dự án: GMO_management_student
 * Tên class StudentService.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository repo;

    /**
     * @return danh sách sinh viên
     * mô tả: lấy ra danh sách sinh viên
     */
    @Override
    public List<StudentEntity> findAllStudent() {
        List<StudentEntity> list = repo.findAll();
        return list.isEmpty() ? null : list;
    }
}
