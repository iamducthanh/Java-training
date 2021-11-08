package com.gmo.gmo_management_student.repository;

import com.gmo.gmo_management_student.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
}
