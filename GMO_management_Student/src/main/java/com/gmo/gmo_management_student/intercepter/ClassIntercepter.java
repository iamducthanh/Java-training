package com.gmo.gmo_management_student.intercepter;

import com.gmo.gmo_management_student.entities.ClassEntity;
import com.gmo.gmo_management_student.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Thêm danh sách class vào các trang
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */

@Component
public class ClassIntercepter implements HandlerInterceptor {
    @Autowired
    private IClassService classService;

    /**
     * Thêm thuộc tính thông báo vào các trang
     * @param request yêu cầu
     * @param response trả về
     * @param handler
     * @return true: cho phép chuyển tiếp tới đường dẫn yêu cầu
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<ClassEntity> classEntities = classService.findAllClass();
        request.setAttribute("classes", classEntities);
        return true;
    }
}
