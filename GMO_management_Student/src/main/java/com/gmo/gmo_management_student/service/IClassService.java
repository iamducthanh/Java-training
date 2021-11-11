package com.gmo.gmo_management_student.service;

import com.gmo.gmo_management_student.entities.ClassEntity;

import java.util.List;

/**
 * Liệt kê các phương thức để thao tác với Class
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */

public interface IClassService {
    /**
     * Lấy toàn bộ danh sách lớp
     * @return danh sách lớp
     */
    List<ClassEntity> findAllClass();

    /**
     * Tìm lớp theo id
     * @param id: id của lớp cần tìm
     * @return ClassEntity được tìm thấy theo id
     */
    ClassEntity findClassById(Integer id);
}
