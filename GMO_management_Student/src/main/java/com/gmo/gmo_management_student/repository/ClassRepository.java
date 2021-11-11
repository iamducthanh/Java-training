package com.gmo.gmo_management_student.repository;

import com.gmo.gmo_management_student.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Kế thừa các phương thức viết sẵn để thao tác với cơ sở dữ liệu
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */

public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
}
