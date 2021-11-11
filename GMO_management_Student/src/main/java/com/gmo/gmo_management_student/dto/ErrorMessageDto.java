package com.gmo.gmo_management_student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Định nghĩa các thuộc tính của thông báo để gửi về view
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {
    private Integer row;
    private Integer column;
    private String messageCode;
    private String param;
}
