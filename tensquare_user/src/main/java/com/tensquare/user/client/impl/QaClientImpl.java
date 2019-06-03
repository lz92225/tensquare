package com.tensquare.user.client.impl;

import com.tensquare.user.client.QaClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class QaClientImpl implements QaClient {
    @Override
    public Result findAll() {
        System.out.println("qa微服务关闭或网络出错！");
        return new Result(false, StatusCode.ERROR, "网络出错，请联系管理员！");
    }
}
