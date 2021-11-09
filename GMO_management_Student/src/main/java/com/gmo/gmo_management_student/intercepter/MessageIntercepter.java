package com.gmo.gmo_management_student.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 */
@Component
public class MessageIntercepter implements HandlerInterceptor {

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("message/message_add_student");
        Map<String, String> message = new HashMap<>();
        message.put("C_00_001", rb.getString("C-00-001"));
        message.put("C_00_002", rb.getString("C-00-002"));
        message.put("C_00_003", rb.getString("C-00-003"));
        message.put("C_00_004", rb.getString("C-00-004"));
        message.put("C_00_005", rb.getString("C-00-005"));
        message.put("C_00_006", rb.getString("C-00-006"));
        message.put("C_00_007", rb.getString("C-00-007"));
        message.put("C_00_008", rb.getString("C-00-008"));
        message.put("C_00_009", rb.getString("C-00-009"));
        request.setAttribute("messages", message);
        return true;
    }
}
