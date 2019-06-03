package com.tensquare.user.service;

import com.tensquare.user.mapper.AdminMapper;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.IdWorker;

import javax.annotation.Resource;

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IdWorker idWorker;

    public Admin login(Admin admin) {
        Admin admin1 = adminMapper.findByLoginname(admin.getLoginname());
        if (admin1 == null) {
            throw new RuntimeException("用户名错误！");
        }
        if (encoder.matches(admin1.getPassword(), admin.getPassword())) {
            throw new RuntimeException("密码错误！");
        }
        return admin1;
    }

    public void regist(Admin admin) {
        admin.setId(idWorker.nextId()+"");
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminMapper.regist(admin);
    }

    public void addUser(User user) {
        user.setId(idWorker.nextId()+"");

//        adminMapper.addUser(user);
    }
}
