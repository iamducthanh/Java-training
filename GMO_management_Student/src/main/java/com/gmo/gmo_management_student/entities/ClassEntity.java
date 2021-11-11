package com.gmo.gmo_management_student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Định nghĩa thực thể Class giống với bảng class trong database
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */

@Entity
@Table(name = "class")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "classEntity")
    List<StudentEntity> studentEntities;

}
