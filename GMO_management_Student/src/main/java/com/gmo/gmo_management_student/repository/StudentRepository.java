package com.gmo.gmo_management_student.repository;

import com.gmo.gmo_management_student.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


/**
 * Kế thừa các phương thức viết sẵn để thao tác với cơ sở dữ liệu
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    /**
     * Lấy danh sách sinh viên theo Full name và Date Of Birth
     * @param fullname
     * @param dateOfBirth
     * @return danh sách sinh viên từ cơ sở dữ liệu
     */
    @Query("select o from StudentEntity o where o.fullname = ?1 and o.dateOfBirth = ?2")
    List<StudentEntity> findByFullnameAndDateOfBirth(String fullname, Date dateOfBirth);
}
