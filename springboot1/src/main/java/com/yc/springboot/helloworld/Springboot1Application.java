package com.yc.springboot.helloworld;

import com.yc.springboot.helloworld.properties.YcProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
 // @Controller控制曾  @Restful 以rest规范(get，post 等)发请求和响应
//@EnableConfigurationProperties(YcProperties.class)
public class Springboot1Application {

    public static void main(String[] args) {

             SpringApplication.run(Springboot1Application.class, args);
    }


}


