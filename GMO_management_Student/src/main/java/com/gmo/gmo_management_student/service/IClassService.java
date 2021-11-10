package com.gmo.gmo_management_student.service;

import com.gmo.gmo_management_student.entities.ClassEntity;

import java.util.List;

/**
 * Tên dự án: GMO_management_student
 * Tên class IClassService.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */

public interface IClassService {
    /**
     * @return danh sách lớp
     * mô tả: lấy toàn bộ danh sách lớp
     */
    List<ClassEntity> findAllClass();

    /**
     * @param id: id của lớp cần tìm
     * @return ClassEntity được tìm thấy theo id
     * mô tả: tìm lớp theo id
     */
    ClassEntity findClassById(Integer id);
}
