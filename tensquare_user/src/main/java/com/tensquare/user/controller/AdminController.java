package com.tensquare.user.controller;

import com.tensquare.user.pojo.Admin;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.AdminService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登陆  生成token给前段
     * @param admin
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Admin admin){
        admin = adminService.login(admin);
        //生成token
        String token = jwtUtil.createJWT(admin.getId(), admin.getLoginname(), "admin");
        //用map存放token和角色
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", "admin");
        return new Result(true, StatusCode.OK, "登陆成功", map);
    }

    /**
     * 注册管理员  密码加密存数据库
     * @param admin
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public  Result regist(@RequestBody Admin admin){
        adminService.regist(admin);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user){
        adminService.addUser(user);
        return new Result(true, StatusCode.OK, "添加用户成功");
    }
}
