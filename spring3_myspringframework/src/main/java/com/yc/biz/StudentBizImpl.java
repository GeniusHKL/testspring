package com.yc.biz;

import com.yc.dao.StudentDao;
import com.yc.springframework.stereotype.*;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@MyService
public class StudentBizImpl {

    private StudentDao studentDao;
    //@Inject
    public StudentBizImpl(){
        System.out.println("无参构造方法");
    }

    @MyPostConstruct
    public void setUp(){
        System.out.println("容器构造后的setup");
    }
    @MyPreDestroy
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
    @MyAutowired    //先看类型再看名字   装配
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
}
