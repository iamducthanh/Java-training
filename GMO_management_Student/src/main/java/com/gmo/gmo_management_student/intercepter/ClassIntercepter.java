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

@Component
public class ClassIntercepter implements HandlerInterceptor {
    @Autowired
    private IClassService classService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<ClassEntity> classEntities = classService.findAllClass();
        request.setAttribute("classes", classEntities);
        return true;
    }
}
