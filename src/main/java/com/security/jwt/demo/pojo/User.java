package com.security.jwt.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jlz
 * @className: User
 * @date 2021/11/19 11:21
 * @description  为了减少对实体类的侵入 定义自己的实体类 使用到security的userDetail中去
 **/
@Data
@TableName("tob_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String passwd;

    private String companyName;

    private String email;

    private String mobile;

    private Boolean enabled;
}
