package com.zyc.skillijserver.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created on 2018/7/23.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Configuration
@EnableWebSecurity
@Component
@EnableGlobalMethodSecurity(jsr250Enabled=true)//可以在方法上加注解设置权限
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//成功登录后，spring security为了防止CSRF攻击，需要在每个页面中验证成功登录后创建的csrf token值，而在静态页面中又无法传递这个token
        http.headers().frameOptions().disable();//此项目中前端使用frame套页面，因此这里要禁掉
        http.authorizeRequests()
                .antMatchers("/**").permitAll()//静态资源都可以访问
//                .anyRequest().authenticated()//任何请求，登陆后才可以访问
                .and()
                //开启cookie保存用户数据
                .rememberMe()
                //设置cookie有效期
                .tokenValiditySeconds(60 * 60 * 24 * 7)
        ;

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserDetailsService());//验证用户信息

    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }


    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }


}
