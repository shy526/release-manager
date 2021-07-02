package com.github.sunjiaqing.dubbo.impl;

import com.github.sunjiaqing.dubbo.ProductService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class ProductServiceImpl implements ProductService {
    @Override
    public String sayHello() {
        return this.getClass().getSimpleName();
    }
}
