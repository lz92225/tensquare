package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendsms(String mobile) {
        //生成随机六位验证码
        String checkcode = RandomStringUtils.randomNumeric(6);
        //缓存放一份
        redisTemplate.opsForValue().set("checkcode_" + mobile, checkcode,6, TimeUnit.HOURS);
        //消息队列放一份
        Map<String, String> map = new HashMap<>();
        map.put("checkcode", checkcode);
        map.put("mobile", mobile);
        rabbitTemplate.convertAndSend("sms", map);
        System.out.println("验证码：" + checkcode);
    }

    public void add(User user) {
        user.setId(idWorker.nextId() + "");
        userDao.save(user);
    }
}
