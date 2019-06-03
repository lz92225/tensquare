package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class ManagerFilter extends ZuulFilter {

    /**
     * 请求前pre过滤还是请求后post过滤
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级  数值越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启此过滤器  true开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内需要执行的操作，返回任意object则为放行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过web网关过滤器！");
        return null;
    }
}
