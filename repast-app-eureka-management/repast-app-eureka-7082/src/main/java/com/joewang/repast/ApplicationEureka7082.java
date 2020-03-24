package com.joewang.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-24
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka7082 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka7082.class, args);
    }
}
