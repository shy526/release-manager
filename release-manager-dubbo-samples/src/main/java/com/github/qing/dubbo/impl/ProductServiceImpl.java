package com.github.qing.dubbo.impl;

import com.github.qing.dubbo.ProductService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class ProductServiceImpl implements ProductService {
    @Override
    public String sayHello() {
        return this.getClass().getSimpleName();
    }
}
