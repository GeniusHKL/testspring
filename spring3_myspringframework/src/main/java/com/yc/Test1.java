package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.biz.StudentBizImpl;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;



public class Test1 {
    public static void main(String[] args) {
        MyApplicationContext ac=new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        StudentBizImpl hw=(StudentBizImpl) ac.getBean("studentBizImpl");
        hw.add("aaa");
    }
}
