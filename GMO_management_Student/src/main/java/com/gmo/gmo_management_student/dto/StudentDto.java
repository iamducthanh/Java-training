package com.gmo.gmo_management_student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Tên dự án: GMO_management_student
 * Tên class StudentDto.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotBlank(message = "C-00-002")
    @NotEmpty(message = "C-00-002")
    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String classId;
    @NotBlank(message = "C-00-002")
    private String dateOfBirth;
    @NotBlank(message = "C-00-002")
    @Length(max = 25, message = "C-00-004")
    private String fullname;
    private String note;
    @NotBlank(message = "C-00-002")
    private String phone;
    @NotBlank(message = "C-00-002")
    private Integer sex;
}
