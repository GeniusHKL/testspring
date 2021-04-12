package com.yc.springframework;

import com.yc.AppConfig;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class StudentBizImplTest {
    ApplicationContext ac;
    private StudentBizImpl studentBiz;

    @Before
    public void setUp() {
        ac= new AnnotationConfigApplicationContext(AppConfig.class);
        studentBiz=(StudentBizImpl) ac.getBean("studentBizImpl");
    }
    @Test
    public void testAdd() {
        studentBiz.add("李四");
    }
    @Test
    public void testUpdate() {
        studentBiz.update("李四");
    }

}