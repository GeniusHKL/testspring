package com.yc.springframework;

import com.yc.AppConfig;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class HelloWorldTest extends TestCase {  //测试用例
    private ApplicationContext ac; //spring 容器
    @Override
    @Before
    public void setUp(){
        ac=new AnnotationConfigApplicationContext(AppConfig.class); //基于注解的配置容器类
        //读取AppConfig.class  basePackage =“com.yc”   得到要扫描的包
        //检查这些包中是否有@component注解 有则实例化
        //存到哟个Map《String，Object》 ==ac
    }
    @Test
    public void testHello() {

        //spring 容器是单例模型
        HelloWorld hw=(HelloWorld)ac.getBean("helloWorld");
        hw.Hello();
        HelloWorld hw2=(HelloWorld)ac.getBean("helloWorld");
        hw2.Hello();
    }
}