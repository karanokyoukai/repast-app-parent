package com.joewang.repast.config;

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

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * @desc: 设置API描述信息
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: []
     * @return: springfox.documentation.service.ApiInfo
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("微信小程序项目服务接口")
                .description("提供了项目中所有接口信息")
                .contact(new Contact("Joe Wang", "NoWhere", "shadowsliver@outlook.com"))
                .version("1.0 beta")
                .build();
    }

    @Bean
    public Docket createRestAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select() //查询对外所要提供的接口，即consumer项目中的controller
                .apis(RequestHandlerSelectors.basePackage("com.joewang.repast.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
