package com.security.jwt.demo.security;

import com.security.jwt.demo.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author clearlove
 * @ClassName SecurityUtils.java
 * @Description 用于获取当前上下文
 * @createTime 2021年11月20日 12:35:00
 */
@Component
public class SecurityUtils {

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
