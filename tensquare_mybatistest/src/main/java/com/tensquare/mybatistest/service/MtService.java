package com.tensquare.mybatistest.service;

import com.tensquare.mybatistest.mapper.MtMapper;
import com.tensquare.mybatistest.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MtService {

    @Resource
    private  MtMapper mapper;

    public Book get(String id) {
        return mapper.getById(id);        
    }

    public void post() {
    }
}
