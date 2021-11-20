package com.security.jwt.demo.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author clearlove
 * @ClassName UserAccessDeniedHandler.java
 * @Description 认证用户但访问了未授权的资源
 * @createTime 2021年11月20日 11:38:00
 */
@Configuration
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        Map<String, String> map = new HashMap<>();
        map.put("code", "403");
        map.put("msg", "暂无权限");
        map.put("data", null);
        httpServletResponse.getWriter().write(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
    }
}
