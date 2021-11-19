package com.security.jwt.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.jwt.demo.pojo.User;
import com.security.jwt.demo.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from tob_user where username = #{username}")
    User loadUserByUsername(String username);
}
