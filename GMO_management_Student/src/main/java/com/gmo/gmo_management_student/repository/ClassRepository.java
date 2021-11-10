package com.gmo.gmo_management_student.repository;

import com.gmo.gmo_management_student.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Tên dự án: GMO_management_student
 * Tên class ClassRepository.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */

public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
}
