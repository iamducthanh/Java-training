package com.gmo.gmo_management_student.service.impl;

import com.gmo.gmo_management_student.entities.ClassEntity;
import com.gmo.gmo_management_student.repository.ClassRepository;
import com.gmo.gmo_management_student.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Định nghĩa lại cho các phương thức để thao tác với Class
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */
@Service
public class ClassService implements IClassService {
    @Autowired
    private ClassRepository repo;

    /**
     * Lấy ra toàn bộ class có trong database
     * @return: danh sách class
     */
    @Override
    public List<ClassEntity> findAllClass() {
        List<ClassEntity> list = repo.findAll();
        return list.isEmpty() ? null : list;
    }

    /**
     * Lấy ra class theo id
     * @param id: id của lớp cần tìm
     * @return nếu tìm thấy thì trả về class không thì trả về rỗng
     */
    @Override
    public ClassEntity findClassById(Integer id) {
        Optional<ClassEntity> classEntity = repo.findById(id);
        return classEntity.isEmpty() ? null : classEntity.get();
    }
}
