package com.security.jwt.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jlz
 * @className: Role
 * @date 2021/11/19 11:49
 * @description todo
 **/
@Data
@TableName("tob_role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String value;
}
