package com.yc.springframework;

import com.yc.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class StudentBizImplTest2 implements ApplicationContextAware {

    @Autowired
    private StudentBizImpl studentBiz;

    private ApplicationContext ac;
    @Test
    public void testAdd() {
        Environment en=ac.getEnvironment();
        System.out.println(en.getProperty("user.dir"));
        studentBiz.add("李四");

    }
    @Test
    public void testUpdate() {
        studentBiz.update("李四");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac=applicationContext;
    }
}

