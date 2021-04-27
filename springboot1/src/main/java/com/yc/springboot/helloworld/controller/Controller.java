package com.yc.springboot.helloworld.controller;

import com.yc.springboot.helloworld.properties.YcProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private static Log log= LogFactory.getLog(Controller.class);

    @Value("${yc.tname}")
    private String tname;

    @Autowired
    private Environment env;

    @Autowired
    private YcProperties ycProperties;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",defaultValue = "World") String name){
        log.debug("**********debug*********");
        log.error("**********error*********");
        log.info("**********info*********");
        log.fatal("**********fatal*********");
        log.info("系统环境变量如下："+env);
        log.info("用户路径："+env.getProperty("user.home"));
        log.info("yc.tname:"+tname);
        log.info("YcProperties:"+ycProperties.getTname()+"\t"+ycProperties.getAge());
        log.info("env中是否也可以拿到："+env.getProperty("yc.tname")+env.getProperty("yc.age"));
        return String.format("hello %s",name);
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "你好";
    }
}
