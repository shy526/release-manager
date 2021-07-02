package com.github.sunjiaqing.dubbo.impl;

import com.github.sunjiaqing.dubbo.OrdertService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class OrderServiceImpl implements OrdertService {
    @Override
    public String sayHello() {
        return this.getClass().getSimpleName();
    }
}
