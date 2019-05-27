package com.tensquare.base.controller;

import com.tensquare.base.pojo.Lable;
import com.tensquare.base.service.LableService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lable")
public class LableController {

    @Autowired
    private LableService lableService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", lableService.findAll());
    }

    @RequestMapping(value = "/{lableId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String lableId) {
        return new Result(true, StatusCode.OK, "查询成功", lableService.findById(lableId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Lable lable) {
        lableService.add(lable);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{lableId}", method = RequestMethod.PUT)
    public Result update(@RequestBody Lable lable, @PathVariable String lableId) {
        lable.setId(lableId);
        lableService.update(lable);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @RequestMapping(value = "/{lableId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String lableId) {
        lableService.delete(lableId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchPage(@RequestBody Lable lable, @PathVariable int page, @PathVariable int size){
        PageResult pageResult = new PageResult();
        pageResult = lableService.searchPage(lable, page, size);
        return new Result(true, StatusCode.OK, "分页查询成功",pageResult);
    }

    /*@RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Lable lable) {
        List<Lable> list = lableService.findSearch(lable);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result pageSearch(@PathVariable int page, @PathVariable int size, @RequestBody Lable lable) {
        Page<Lable> p = lableService.pageSearch(page, size, lable);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Lable>(p.getTotalElements(), p.getContent()));
    }*/
}
