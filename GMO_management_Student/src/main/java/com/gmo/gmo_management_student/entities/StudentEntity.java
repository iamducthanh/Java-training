package com.gmo.gmo_management_student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;
    private Date dateOfBirth;
    private Boolean sex;
    private String phone;
    private String note;
}
