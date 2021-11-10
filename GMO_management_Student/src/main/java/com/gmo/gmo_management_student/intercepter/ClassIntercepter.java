package com.gmo.gmo_management_student.intercepter;

import com.gmo.gmo_management_student.entities.ClassEntity;
import com.gmo.gmo_management_student.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Tên dự án: GMO_management_student
 * Tên class ClassIntercepter.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */

@Component
public class ClassIntercepter implements HandlerInterceptor {
    @Autowired
    private IClassService classService;

    /**
     * @param request
     * @param response
     * @param handler
     * @return true: cho phép chuyển tiếp tới đường dẫn yêu cầu
     * @throws Exception
     * Mô tả thêm thuộc tính thông báo vào các trang
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<ClassEntity> classEntities = classService.findAllClass();
        request.setAttribute("classes", classEntities);
        return true;
    }
}
