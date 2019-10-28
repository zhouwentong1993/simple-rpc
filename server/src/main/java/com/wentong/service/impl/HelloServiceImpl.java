package com.wentong.service.impl;

import com.wentong.service.HelloService;

public class HelloServiceImpl implements HelloService {

    public void sayHello(String name) {
        System.out.println("hello " + name);
    }
}
