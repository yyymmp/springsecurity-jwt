package com.security.jwt.demo.security;

import com.security.jwt.demo.mapper.UserRoleMapper;
import com.security.jwt.demo.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jlz
 * @className: UserSecurity
 * @date 2021/11/19 12:00
 * @description todo
 **/
@Data
public class UserSecurity implements UserDetails {

    UserRoleMapper userRoleMapper;

    /**
     * 自己的user
     */
    private User user;

    public UserSecurity(User user, UserRoleMapper userRoleMapper) {
        this.user = user;
        this.userRoleMapper = userRoleMapper;
    }

    /**
     * 获取用户角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = userRoleMapper.getRoleByUserId(user.getId());
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswd();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
