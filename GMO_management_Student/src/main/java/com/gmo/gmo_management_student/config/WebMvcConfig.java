package com.gmo.gmo_management_student.config;

import com.gmo.gmo_management_student.intercepter.ClassIntercepter;
import com.gmo.gmo_management_student.intercepter.MessageIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Thêm các phương thức lấy dữ liệu trước khi xử lí yêu cầu
 * @author  GMO_ThanhND
 * @version 1.0
 * @since   2021-11-08
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ClassIntercepter classIntercepter;
    @Autowired
    private MessageIntercepter messageIntercepter;

    /**
     * Gọi phương thức thêm thuộc tính cho các trang hiển thị
     * @param registry kho chứa các Intercepter
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(classIntercepter).addPathPatterns("/**");
        registry.addInterceptor(messageIntercepter).addPathPatterns("/add-student/**");
    }
}
