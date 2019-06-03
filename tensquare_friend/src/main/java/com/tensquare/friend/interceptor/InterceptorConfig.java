package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterceptorConfig implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    //进入controller前拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器");
        //token加到请求头里边
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.length() > 0) {
            if (authorization.startsWith("Bearer ")) {
                String token = authorization.substring(7);
                Claims claims = jwtUtil.parseJWT(token);
                String roles = (String) claims.get("roles");
                if (roles != null && roles.length() > 0) {
                    if (roles.equals("user")) {
                        request.setAttribute("user_claims", claims);
                    }
                    if (roles.equals("admin")) {
                        request.setAttribute("admin_claims", claims);
                    }
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
}
