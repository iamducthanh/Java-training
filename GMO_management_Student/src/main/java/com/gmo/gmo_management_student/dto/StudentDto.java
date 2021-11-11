package com.gmo.gmo_management_student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Integer rowId;
    @NotBlank(message = "C-00-002")
    private String classId;

    @NotBlank(message = "C-00-002")
    private String dateOfBirth;

    @NotBlank(message = "C-00-002")
    @Length(max = 25, message = "C-00-004")
    @Pattern(regexp = "[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+$", message = "C-00-003")
    private String fullname;

    private String note;

    @NotBlank(message = "C-00-002")
    @Pattern(regexp = "[0]{1}[3245879]{1}[0-9]{8}", message = "C-00-007")
    private String phone;

    @NotBlank(message = "C-00-002")
    @Pattern(regexp = "[01]{1}", message = "C-00-006")
    private String sex;

    public Date getDateOfBirthDate() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateOfBirth);
    }
}
