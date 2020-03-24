package com.joewang.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.joewang.repast"})
public class ApplicationZuul4081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationZuul4081.class, args);
    }
}
