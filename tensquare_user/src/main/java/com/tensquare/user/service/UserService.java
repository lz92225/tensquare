package com.tensquare.user.service;

import com.tensquare.user.mapper.UserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.annotation.Resource;

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

    public void sendsms(String mobile) {
        //生成随机六位验证码
        String checkcode = RandomStringUtils.randomNumeric(6);
        //缓存放一份
//        redisTemplate.opsForValue().set("checkcode_" + mobile, checkcode,6, TimeUnit.HOURS);
        //消息队列放一份
//        Map<String, String> map = new HashMap<>();
//        map.put("checkcode", checkcode);
//        map.put("mobile", mobile);
//        rabbitTemplate.convertAndSend("sms", map);
        System.out.println("验证码：" + checkcode);
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
