package com.security.jwt.demo.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jlz
 * @className: UserAuthenticationEntryPoint
 * @date 2021/11/19 18:18
 * @description 未登录访问接口时 即未进行token认证
 * 用于匿名用户访问受保护的资源时
 **/
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String ,String> map = new HashMap<>();
        map.put("code","403");
        map.put("msg","请先登录");
        map.put("data",null);
        httpServletResponse.getWriter().write(JSON.toJSONString(map));
    }
}
