package com.gmo.gmo_management_student.dto;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.*;

/**
 * Tên dự án: GMO_management_student
 * Tên class ValidList.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */
@Data
public class ValidList<E> implements List<E> {
    @Valid
    @Delegate
    private List<E> list = new ArrayList<>();
}