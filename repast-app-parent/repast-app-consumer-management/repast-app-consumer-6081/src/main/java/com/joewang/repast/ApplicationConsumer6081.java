package com.joewang.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ApplicationConsumer6081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumer6081.class, args);
    }
}
