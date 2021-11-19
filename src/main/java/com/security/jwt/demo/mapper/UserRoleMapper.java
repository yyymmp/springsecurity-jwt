package com.security.jwt.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据id获取用户角色
     * @param userId
     * @return
     */
    @Select("select tob_role.value from tob_user_role left join tob_role on tob_user_role.role_id = tob_role.id")
    List<String> getRoleByUserId(int userId);
}
