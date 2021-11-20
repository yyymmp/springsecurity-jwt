package com.security.jwt.demo.filter;

import com.security.jwt.demo.security.UserSecurity;
import com.security.jwt.demo.security.UserService;
import com.security.jwt.demo.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author jlz
 * @className: JwtRequestFilter
 * @date 2021/11/19 16:54
 * @description todo
 **/
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        // JWT Token 获取请求头部的 Bearer
        System.out.println("filter:header:" + requestTokenHeader);
        //判断，从token中得到username
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            //System.out.println("filter :requestTokenHeader not null and start with bearer");
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = JwtTokenUtils.getUserName(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            } catch (MalformedJwtException e) {
                System.out.println("JWT Token MalformedJwtException");
            }
        } else {
            //System.out.println("filter :requestTokenHeader is null || not start with bearer");
            //logger.warn("JWT Token does not begin with Bearer String");
        }

        // 验证,username,如果验证合法则保存到SecurityContextHolder
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //System.out.println("filter:username!=null");
            UserSecurity userSecurity = ((UserSecurity) userService.loadUserByUsername(username));
            // JWT 验证通过 使用Spring Security 管理
            //将当前用户 userSecurity.getUser()存入上下文以便在其他地方获取
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userSecurity.getUser(), null, userSecurity.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        chain.doFilter(request, response);
    }
}
