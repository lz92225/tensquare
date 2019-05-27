package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //authorizeRequests所有的security全注解配置实现的开端，表示开始说明需要的权限
        //权限分两部分，1：拦截的路径；2：访问路径需要的权限

        //antMatchers拦截的路径   permitAll全部放行

        //anyRequest所有的请求   authenticated认证后才能访问

        //.and().csrf().disable()固定写死，使csrf拦截失效。
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
