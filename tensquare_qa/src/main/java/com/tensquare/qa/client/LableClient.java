package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquare-base")
public interface LableClient {

    @RequestMapping(value = "/lable/{lableId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("lableId") String lableId);

}
