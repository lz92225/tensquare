package com.tensquare.user.service;

import com.tensquare.user.mapper.UserMapper;
import com.tensquare.user.pojo.User;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.IdWorker;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest request;

    public void sendsms(String mobile) {
        //生成随机六位验证码
        String checkcode = RandomStringUtils.randomNumeric(6);
        //缓存放一份
        redisTemplate.opsForValue().set("checkcode_" + mobile, checkcode, 1, TimeUnit.HOURS);
        //消息队列放一份
        Map<String, String> map = new HashMap<>();
        map.put("checkcode", checkcode);
        map.put("mobile", mobile);
//        rabbitTemplate.convertAndSend("sms", map);
        System.out.println("验证码：" + checkcode);
    }

    public User login(@RequestBody User user) {
        User user1 = userMapper.login(user.getNickname());
        if (user1 == null) {
            throw new RuntimeException("用户不存在！");
        }
        if (!encoder.matches(user.getPassword(), user1.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        return user1;
    }

    public void add(User user) {
        user.setId(idWorker.nextId() + "");
        userMapper.addUser(user);
    }

    public List<User> findAll() {
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            throw new RuntimeException("权限不足，请先登陆后再查询！");
        }
        String roles = (String) claims.get("roles");
        if (!"user".equals(roles)) {
            throw new RuntimeException("权限不足，请先登陆后再查询！");
        }
        return userMapper.findAll();
    }

    public User selectByNickname(String nickname) {
        return userMapper.selectByNickname(nickname);
    }

    /*public void add(User user) {
        user.setId(idWorker.nextId() + "");
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(String userId) {
        return userDao.findById(userId).get();
    }

    public void update(User user) {
        userDao.save(user);
    }

    public void delete(String userId) {
        userDao.deleteById(userId);
    }

    public List<User> findSearch(User user) {
        userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(user.getNickname()!=null && user.getNickname().length()>0){
                    Predicate predicate = null;
                }
                return null;
            }
        });
        return null;
    }

    public Page<User> pageSearch(int page, int size, User user) {
        return null;
    }*/
}
