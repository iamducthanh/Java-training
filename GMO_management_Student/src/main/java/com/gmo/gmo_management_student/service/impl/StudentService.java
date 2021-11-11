package com.gmo.gmo_management_student.service.impl;

import com.gmo.gmo_management_student.entities.StudentEntity;
import com.gmo.gmo_management_student.repository.StudentRepository;
import com.gmo.gmo_management_student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Định nghĩa lại cho các phương thức để thao tác với Student
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */
@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository repo;

    /**
     * Lấy ra danh sách sinh viên
     * @return nếu tìm thấy thì trả về danh sách sinh viên, không thì trả về rỗng
     */
    @Override
    public List<StudentEntity> findAllStudent() {
        List<StudentEntity> list = repo.findAll();
        return list.isEmpty() ? null : list;
    }

    /**
     * Lưu danh sách sinh viên vào database
     * @param studentEntities danh sách thực thể sinh viên
     */
    @Override
    public void saveStudents(List<StudentEntity> studentEntities) {
        repo.saveAll(studentEntities);
    }

    /**
     * Tìm sinh viên theo họ tên vào ngày sinh
     * @param fullname họ tên sinh viên
     * @param dateOfBirth ngày sinh
     * @return trả về sinh viên tìm thấy
     */
    @Override
    public StudentEntity findStudentByFullnameAndDateOfBirth(String fullname, Date dateOfBirth) {
        List<StudentEntity> list = repo.findByFullnameAndDateOfBirth(fullname, dateOfBirth);
        return list.isEmpty() ? null : list.get(0);
    }
}
