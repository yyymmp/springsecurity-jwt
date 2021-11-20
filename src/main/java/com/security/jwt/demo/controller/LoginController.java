package com.security.jwt.demo.controller;

import com.security.jwt.demo.pojo.Response;
import com.security.jwt.demo.pojo.User;
import com.security.jwt.demo.security.SecurityUtils;
import com.security.jwt.demo.security.UserSecurity;
import com.security.jwt.demo.security.UserService;
import com.security.jwt.demo.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Api(tags = "登录模块")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtils securityUtils;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Response<String> login(@RequestParam("username") @ApiParam("用户名") String username, @RequestParam("password") @ApiParam("用户密码") String password,
            HttpServletRequest req) {
        try {
            //这里登录成功后会将userDetails放入security上下文中
            req.login(username, password);
            //正常执行则登录成功
            UserSecurity userDetails = ((UserSecurity) userService.loadUserByUsername(username));
            String token = JwtTokenUtils.createToken(username, userDetails.getUser().getId());
            return Response.success(token, "login success");
        } catch (Exception e) {
            //将隐藏异常设置为false后 在这里可以根据e的具体类型来判断具体返回
            if (e.getCause() instanceof UsernameNotFoundException) {
                //用户未找到
                return Response.error("用户名或密码错误");
            }
            if (e.getCause() instanceof BadCredentialsException) {
                //用户未找到
                return Response.error("密码错误");
            }
            //其他异常
            return Response.error("登录失败");
        }

    }

    @GetMapping("/hello")
    public Response<String> hello() {
        User currentUser = securityUtils.getCurrentUser();
        System.out.println(currentUser.getUsername() + currentUser.getId());
        return Response.success("hello");
    }


    @GetMapping("/admin/hello")
    public Response<String> adminHello() {
        User currentUser = securityUtils.getCurrentUser();
        System.out.println(currentUser.getUsername() + currentUser.getId());
        return Response.success("hello");
    }

    @Secured({"user","admin"})
    @GetMapping("/user/hello")
    public Response<String> userHello() {
        User currentUser = securityUtils.getCurrentUser();
        System.out.println(currentUser.getUsername() + currentUser.getId());
        return Response.success("hello");
    }
}

