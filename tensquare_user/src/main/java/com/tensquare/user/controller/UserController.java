package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;
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
        //密码加密
        user.setPassword(encoder.encode(user.getPassword()));
        userService.add(user);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String userId) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        userService.add(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String userId) {
        user.setId(userId);
        userService.update(user);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String userId) {
        userService.delete(userId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody User user) {
        List<User> list = userService.findSearch(user);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result pageSearch(@PathVariable int page, @PathVariable int size, @RequestBody User user) {
        Page<User> p = userService.pageSearch(page, size, user);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(p.getTotalElements(), p.getContent()));
    }
}
