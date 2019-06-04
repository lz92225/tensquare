package com.tensquare.user.controller;

import com.tensquare.user.client.QaClient;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private QaClient qaClient;

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 查看所有问题
     */
    @RequestMapping(value = "/problem", method = RequestMethod.GET)
    public Result findAllProblem(){
        return qaClient.findAll();
    }
    /**
     * 登陆 生成token
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        user = userService.login(user);
        String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
        Map<String, Object> map = new HashMap();
        map.put("token", token);
        map.put("roles", "user");
        return new Result(true, StatusCode.OK, "登陆成功", map);
    }
    /**
     * 生成验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.GET)
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
        System.out.println(checkcode);
        System.out.println(code);
        if(checkcode == null || checkcode.length() == 0){
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
//
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }
//
//    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
//    public Result findById(@PathVariable String userId) {
//        return new Result(true, StatusCode.OK, "查询成功", userService.findById(userId));
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public Result add(@RequestBody User user) {
//        userService.add(user);
//        return new Result(true, StatusCode.OK, "添加成功");
//    }
//
//    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
//    public Result update(@RequestBody User user, @PathVariable String userId) {
//        user.setId(userId);
//        userService.update(user);
//        return new Result(true, StatusCode.OK, "更新成功");
//    }
//
//    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
//    public Result deleteById(@PathVariable String userId) {
//        userService.delete(userId);
//        return new Result(true, StatusCode.OK, "删除成功");
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    public Result findSearch(@RequestBody User user) {
//        List<User> list = userService.findSearch(user);
//        return new Result(true, StatusCode.OK, "查询成功", list);
//    }
//
//    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
//    public Result pageSearch(@PathVariable int page, @PathVariable int size, @RequestBody User user) {
//        Page<User> p = userService.pageSearch(page, size, user);
//        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(p.getTotalElements(), p.getContent()));
//    }
}
