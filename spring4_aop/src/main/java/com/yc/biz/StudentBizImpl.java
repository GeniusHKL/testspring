package com.yc.biz;

import com.yc.dao.StudentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Service
public class StudentBizImpl implements StudentBiz{

    private StudentDao studentDao;
    //@Inject
    public StudentBizImpl(){
        System.out.println("无参构造方法");
    }

    @PostConstruct
    public void setUp(){
        System.out.println("容器构造后的setup");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("容器销毁前");
    }
    public StudentBizImpl(StudentDao studentDao){
        this.studentDao=studentDao;
    }

//1.    @Inject  javax中的依赖注入，  如果有多个对象   如jap和mybatisimpl对象
//     这里要用@Named("StudentMybatisImpl")  一个对象则不用

    //2.@Autowired
    //@Qualifier("StudentMybatisImpl")  //autowired 用qualifier
//3.  公共交通  绿色都市  大学城  不夜城  公园生活
    //@MyResource(name="studentDaoJpaImpl")
//    @Autowired    //先看类型再看名字   装配
//    @Qualifier("studentMybatisImpl")
    //@Resource  //studentMybatisImpl
    @Resource(name="studentDaoJpaImpl")   //studentDaojpaImpl
    public void setStudentDao( StudentDao studentDao){
        this.studentDao=studentDao;
    }

    public int add(String name){
        System.out.println("=======业务层==========");
        System.out.println("用户名是否重名");
        int result=studentDao.add(name);
        System.out.println("========业务操作结束==========");
        return result;
    }
    public void update(String name){
        System.out.println("=======业务层gengxin==========");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("=========业务操作结束===========");

    }
    public  void find(String name){
        System.out.println("=======业务层查询==========");
        System.out.println("用户名是否重名");
        studentDao.find(name);
        System.out.println("=========业务操作结束===========");
    }
}
