package com.yc.dao;

import com.yc.springframework.StudentBizImpl;
import junit.framework.TestCase;

public class StudentDaoTest extends TestCase {
    private StudentDao studentDao;
    private StudentBizImpl studentBiz;

    @Override
    public void setUp() throws Exception{
        studentDao = new StudentMybatisImpl();
        studentBiz =new StudentBizImpl();
        studentBiz.setStudentDao(studentDao);

    }

    public void testAdd() {
        studentDao.add("张三");
    }
    public void testUpdate() {
        studentDao.update("张三");
    }

    public void testBizAdd(){
        studentBiz.add("张三");
    }
    public void testBizUpdate(){
        studentBiz.update("张三");
    }
}