package com.gmo.gmo_management_student.service;

import com.gmo.gmo_management_student.entities.ClassEntity;

import java.util.List;

public interface IClassService {
    List<ClassEntity> findAllClass();
}
