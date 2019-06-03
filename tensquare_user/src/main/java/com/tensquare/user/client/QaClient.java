package com.tensquare.user.client;

import com.tensquare.user.client.impl.QaClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tensquare-qa", fallback = QaClientImpl.class)
public interface QaClient {

    @RequestMapping(value = "/problem",method= RequestMethod.GET)
    public Result findAll();
}
