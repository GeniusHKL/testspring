package com.yc.springframework;

import org.springframework.stereotype.Component;

@Component    //只要加了这个注解，则这个类可以被spring容器托管
public class HelloWorld {
    public HelloWorld(){System.out.println("无参构造方法");}

    public void Hello(){System.out.println("hello world");}
}
