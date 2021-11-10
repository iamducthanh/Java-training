package com.gmo.gmo_management_student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tên dự án: GMO_management_student
 * Tên class MessageError.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {
    private Integer row;
    private Integer colum;
    private String messageCode;
}
