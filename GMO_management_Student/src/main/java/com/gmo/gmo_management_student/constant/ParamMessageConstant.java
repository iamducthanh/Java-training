package com.gmo.gmo_management_student.constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tên dự án: GMO_management_student
 * Tên class ParamMessageConstant.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */
public class ParamMessageConstant {
    public static void main(String[] args) throws ParseException {
        String dfa = "";
        String sDate1="2021-11-10";
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
        System.out.println(date1);
    }
}
