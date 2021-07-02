package com.github.qing.dubbo.impl;

import com.github.qing.dubbo.OrdertService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class OrderServiceImpl implements OrdertService {
    @Override
    public String sayHello() {
        return this.getClass().getSimpleName();
    }
}
