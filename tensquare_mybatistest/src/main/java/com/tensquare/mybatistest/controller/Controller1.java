package com.tensquare.mybatistest.controller;

import com.tensquare.mybatistest.pojo.Book;
import com.tensquare.mybatistest.service.MtService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Controller1 {

    @Autowired
    private MtService service;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result getTest(@PathVariable String id){
        Book book = service.get(id);
        return new Result(true, StatusCode.OK,"get  ok", book);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Result postTest(){
        service.post();
        return new Result(true, StatusCode.OK,"post  ok");
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public Result putTest(){
        return new Result(true, StatusCode.OK,"put  ok");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result deleteTest(){
        return new Result(true, StatusCode.OK,"delete  ok");
    }
}
