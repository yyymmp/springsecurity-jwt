package com.security.jwt.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jlz
 * @className: RolePermission
 * @date 2021/11/19 11:53
 * @description todo
 **/
@Data
@TableName("tob_role_permission")
public class RolePermission {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer permissionId;

    private Integer roleId;
}
