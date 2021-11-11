package com.gmo.gmo_management_student.dto;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.*;

/**
 * Sử dụng @Valid cho từng đối tượng trong danh sách
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-09
 */
@Data
public class ValidList<E> implements List<E> {
    @Valid
    @Delegate
    private List<E> list = new ArrayList<>();
}