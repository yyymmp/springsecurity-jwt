package com.security.jwt.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jlz
 * @className: Permission
 * @date 2021/11/19 11:50
 * @description todo
 **/
@Data
@TableName("tob_permission")
public class Permission {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer pid;

    private String  name;

    private String  url;
}
