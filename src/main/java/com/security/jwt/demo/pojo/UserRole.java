package com.security.jwt.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jlz
 * @className: UserRole
 * @date 2021/11/19 11:52
 * @description todo
 **/
@Data
@TableName("tob_user_role")
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer roleId;
}
