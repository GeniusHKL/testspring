package com.yc.bean;

import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestroy;

@MyComponent
public class HelloWorld {

    @MyPostConstruct
    public void setUp(){
        System.out.println("MyPostConstruct");
    }

    @MyPreDestroy
    public void destroy(){
        System.out.println("MyPreDestroy");
    }

    public HelloWorld(){

        System.out.println("hello 构造");
    }

    public void show(){
        System.out.println("show");
    }

}
