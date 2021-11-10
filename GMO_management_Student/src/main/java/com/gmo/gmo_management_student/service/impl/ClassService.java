package com.gmo.gmo_management_student.service.impl;

import com.gmo.gmo_management_student.entities.ClassEntity;
import com.gmo.gmo_management_student.repository.ClassRepository;
import com.gmo.gmo_management_student.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService implements IClassService {
    @Autowired
    private ClassRepository repo;

    /**
     * @return: danh sách class
     */
    @Override
    public List<ClassEntity> findAllClass() {
        List<ClassEntity> list = repo.findAll();
        return list.isEmpty() ? null : list;
    }

    @Override
    public ClassEntity findClassById(Integer id) {
        Optional<ClassEntity> classEntity = repo.findById(id);
        return classEntity.isEmpty() ? null : classEntity.get();
    }
}
