package com.gmo.gmo_management_student.service.impl;

import com.gmo.gmo_management_student.entities.ClassEntity;
import com.gmo.gmo_management_student.repository.ClassRepository;
import com.gmo.gmo_management_student.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService implements IClassService {
    @Autowired
    private ClassRepository repo;

    /*
    Mô tả: lấy danh sách lớp học
    @Param: null
    @Return: danh sách lớp
     */
    @Override
    public List<ClassEntity> findAllClass() {
        List<ClassEntity> list = repo.findAll();
        return list.isEmpty() ? null : list;
    }
}
