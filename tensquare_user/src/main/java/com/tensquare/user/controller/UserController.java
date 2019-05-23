package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 生成验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendsms(@PathVariable String mobile){
        userService.sendsms(mobile);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
    public Result register(@PathVariable String code, @RequestBody User user){
        String checkcode = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
        if(checkcode.isEmpty()){
            return new Result(false, StatusCode.ERROR,"请获取验证码");
        }
        if(!checkcode.equals(code)){
            return new Result(false, StatusCode.ERROR,"请输入正确的验证码");
        }
        userService.add(user);
        return new Result(true, StatusCode.OK, "注册成功");
    }
}
