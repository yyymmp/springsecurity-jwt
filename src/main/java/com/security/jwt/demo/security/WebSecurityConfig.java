package com.security.jwt.demo.security;

import com.security.jwt.demo.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author jlz
 * @className: WebSecurityConfig
 * @date 2021/11/19 14:56
 * @description todo
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    @Autowired
    UserAccessDeniedHandler userAccessDeniedHandler;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //不加密 已过期
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //不隐藏异常 框架将所有异常最后都隐藏 只抛出一种异常
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(userService);
        //设置加密器 否则login过程中找不到
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源文件
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                //swagger3路径放行
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v3/*").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/doc.html").permitAll()
                //不能加上前缀
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated()
                .and()
//                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                //匿名用户访问未授权资源  即未登录
                .exceptionHandling()
                .authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                //无权访问
                .exceptionHandling()
                .accessDeniedHandler(userAccessDeniedHandler)
                .and()
                //jwt过滤器
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    //todo 跨域
}
