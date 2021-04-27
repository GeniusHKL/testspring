package com.yc.tx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value(value="${swagger.enabled}")
    private boolean swaggerEnable;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnable)  //强制控制是否打开swagger2功能
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yc"))
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    /**
     * 生成的rest api 的信息对象
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("银行存取操作接口") //设置文档的标题
                .description("银行存取操作 API 接口文档") // 设置文档的描述
                .version("1.0") // 设置文档的版本信息-> 1.0.0 Version information
                .contact(new Contact("张影","http://www.hyycinfo.com","1532516146@qq.com"))
                .termsOfServiceUrl("http://www.hyycinfo.com") // 设置文档的License信息->1.3 License information
                .build();
    }

}
