package com.gmo.gmo_management_student.service;

import com.gmo.gmo_management_student.entities.ClassEntity;
import com.gmo.gmo_management_student.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IClassService {
    List<ClassEntity> findAllClass();
}
