package com.joewang.repast.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@Component
@PropertySource("classpath:properties/redissingle.properties")
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisSingleProperties {
    private String node;
    private Integer maxAttempts;
    private Integer commandTimeout;
}
