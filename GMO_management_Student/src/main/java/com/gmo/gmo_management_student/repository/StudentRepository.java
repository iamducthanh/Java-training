package com.gmo.gmo_management_student.repository;

import com.gmo.gmo_management_student.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


/**
 * Tên dự án: GMO_management_student
 * Tên class StudentRepository.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Query("select o from StudentEntity o where o.fullname = ?1 and o.dateOfBirth = ?2")
    List<StudentEntity> findByFullnameAndDateOfBirth(String fullname, Date dateOfBirth);
}
