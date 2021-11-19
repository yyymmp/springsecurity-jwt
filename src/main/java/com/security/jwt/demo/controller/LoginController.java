package com.security.jwt.demo.controller;

import com.security.jwt.demo.pojo.Response;
import com.security.jwt.demo.security.UserSecurity;
import com.security.jwt.demo.security.UserService;
import com.security.jwt.demo.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jlz
 * @className: LoginController
 * @date 2021/11/19 15:01
 * @description todo
 **/
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response<String> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req){
        try {
            req.login(username, password);
            //正常执行则登录成功
            UserSecurity userDetails = ((UserSecurity) userService.loadUserByUsername(username));
            String token = JwtTokenUtils.createToken(username, userDetails.getUser().getId());
            return Response.success(token,"login success");
        } catch (ServletException e) {
            //将隐藏异常设置为false后 在这里可以根据e的具体类型来判断具体返回
            e.printStackTrace();
        }

        return Response.error("fail");
    }

    @GetMapping("/hello")
    public Response<String> hello(){
        return Response.success("hello");
    }
}

