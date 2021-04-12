package com.yc.dao;


import org.springframework.stereotype.Repository;

import java.util.Random;
@Repository
public class StudentMybatisImpl implements StudentDao {

    @Override
    public int add(String name) {
        {
            System.out.println("Mybatis 添加学生"+name);
            Random r=new Random();
            return r.nextInt();
        }
    }

    @Override
    public void update(String name) {
        System.out.println("mybatis gengxin学生"+name);
    }
}
