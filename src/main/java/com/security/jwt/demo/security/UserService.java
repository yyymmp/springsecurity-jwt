package com.security.jwt.demo.security;

import com.security.jwt.demo.mapper.UserMapper;
import com.security.jwt.demo.mapper.UserRoleMapper;
import com.security.jwt.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author jlz
 * @className: UserService
 * @date 2021/11/19 14:18
 * @description todo
 **/
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;
    /**
     * 最终的登陆会走到这个位置
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (Objects.equals(user,null)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //将封装UserDetails作为数据源返回
        return new UserSecurity(user, userRoleMapper);
    }
}
