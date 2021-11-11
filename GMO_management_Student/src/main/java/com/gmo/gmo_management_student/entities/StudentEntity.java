package com.gmo.gmo_management_student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Định nghĩa thực thể Student giống với bảng student trong database
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */

@Entity
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(columnNames = {"class_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;
    @Column(name = "date_of_birth ")
    private Date dateOfBirth;
    private Boolean sex;
    private String phone;
    private String note;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    /**
     * Hàm tạo không có tham số là id
     * @param fullname
     * @param dateOfBirth
     * @param sex
     * @param phone
     * @param note
     * @param classEntity
     */
    public StudentEntity(String fullname, Date dateOfBirth, Boolean sex, String phone, String note, ClassEntity classEntity) {
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.phone = phone;
        this.note = note;
        this.classEntity = classEntity;
    }
}
