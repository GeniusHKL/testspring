package com.yc.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class HelloWorld {
    @PostConstruct
    public void setup(){
        System.out.println("postConstruct");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("PreDestroy");
    }

}
