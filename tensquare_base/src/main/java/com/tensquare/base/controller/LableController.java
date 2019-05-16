package com.tensquare.base.controller;

import com.tensquare.base.pojo.Lable;
import com.tensquare.base.service.LableService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lable")
public class LableController {


    @Autowired
    private LableService lableService;
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", lableService.findAll());
    }

    @RequestMapping(value = "/{lableId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String lableId) {
        return new Result(true, StatusCode.OK, "查询成功",lableService.findById(lableId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Lable lable) {
        lableService.add(lable);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{lableId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Lable lable, @PathVariable String lableId) {
        lable.setId(lableId);
        lableService.update(lable);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @RequestMapping(value = "/{lableId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String lableId) {
        lableService.delete(lableId);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
