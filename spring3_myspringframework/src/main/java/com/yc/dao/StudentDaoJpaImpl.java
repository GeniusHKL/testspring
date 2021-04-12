package com.yc.dao;

import com.yc.springframework.stereotype.MyRepository;


import java.util.Random;

@MyRepository//异常转换：从exception转化为RuntimeException
public class StudentDaoJpaImpl implements StudentDao {

    @Override
    public int add(String name) {
        {
            System.out.println("jpa 添加学生"+name);
            Random r=new Random();
            return r.nextInt();
        }
    }

    @Override
    public void update(String name) {
        System.out.println("jpa gengxin学生"+name);
    }
}
