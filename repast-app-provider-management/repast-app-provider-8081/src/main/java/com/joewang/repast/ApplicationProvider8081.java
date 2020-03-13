package com.joewang.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@MapperScan("com.joewang.repast.mapper")
public class ApplicationProvider8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider8081.class, args);
    }
}
