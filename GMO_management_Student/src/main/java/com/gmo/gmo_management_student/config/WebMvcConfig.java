package com.gmo.gmo_management_student.config;

import com.gmo.gmo_management_student.intercepter.ClassIntercepter;
import com.gmo.gmo_management_student.intercepter.MessageIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ClassIntercepter classIntercepter;
    @Autowired
    private MessageIntercepter messageIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(classIntercepter).addPathPatterns("/**");
        registry.addInterceptor(messageIntercepter).addPathPatterns("/add-student/**");
    }
}
