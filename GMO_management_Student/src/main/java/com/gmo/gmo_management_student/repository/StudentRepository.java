package com.gmo.gmo_management_student.repository;

import com.gmo.gmo_management_student.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
}
