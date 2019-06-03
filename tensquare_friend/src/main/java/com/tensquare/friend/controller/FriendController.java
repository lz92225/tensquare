package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "/like/{userid}/{friendid}", method = RequestMethod.GET)
    public Result addlike(@PathVariable String userid, @PathVariable String friendid){
        friendService.addlike(userid, friendid);
        return new Result(true, StatusCode.OK, "添加成功");
    }
}
