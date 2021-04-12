package com.yc.biz;

import com.yc.MyAppConfig;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyAppConfig.class)
public class StudentBizImplTest {

    //@Autowired
    @Resource
    private StudentBiz studentBiz;


    @Test
    public void testAdd() {
        studentBiz.add("李四");
    }
    @Test
    public void testUpdate() {
        studentBiz.update("李四");
    }


    @Test
    public void testFind() {
        studentBiz.find("李四");
    }
}