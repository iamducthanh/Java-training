package com.gmo.gmo_management_student.config;

import com.gmo.gmo_management_student.intercepter.ClassIntercepter;
import com.gmo.gmo_management_student.intercepter.MessageIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Tên dự án: GMO_management_student
 * Tên class WebMvcConfig.java
 * Version     date            by              change/comment
 * 1.0         08/11/2021      GMO_ThanhND     create
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ClassIntercepter classIntercepter;
    @Autowired
    private MessageIntercepter messageIntercepter;

    /**
     * @param registry
     * Mô tả: gọi phương thức thêm thuộc tính cho các trang hiển thị
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(classIntercepter).addPathPatterns("/**");
        registry.addInterceptor(messageIntercepter).addPathPatterns("/add-student/**");
    }
}
