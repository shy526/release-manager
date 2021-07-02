package com.github.sunjiaqing.dubbo.impl;

import com.github.sunjiaqing.dubbo.OrdertService;
import com.github.sunjiaqing.dubbo.ProductService;
import com.github.sunjiaqing.dubbo.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class TestServiceImpl implements TestService {
    @Autowired
    private ProductService productService;
    @DubboReference
    private OrdertService ordertService;
    @Override
    public String sayHello() {
        return this.getClass().getSimpleName()+"->"+productService.sayHello();
    }
}
