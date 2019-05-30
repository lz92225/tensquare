package com.tensquare.user.service;

import com.tensquare.user.mapper.AdminMapper;
import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Admin login(Admin admin) {
        Admin admin1 = adminMapper.findByLoginname(admin.getLoginname());
        if(admin1 == null){
            throw new RuntimeException("用户名错误！");
        }
        if(encoder.matches(admin1.getPassword(), admin.getPassword())){
            throw new RuntimeException("密码错误！");
        }
        return admin1;
    }
}
