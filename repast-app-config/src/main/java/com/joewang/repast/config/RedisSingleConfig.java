package com.joewang.repast.config;

import com.joewang.repast.properties.RedisSingleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;


/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@Configuration
public class RedisSingleConfig {
    @Autowired
    private RedisSingleProperties redisSingleProperties;

    @Bean
    public Jedis getJedis(){
        String[] hostAndPort = redisSingleProperties.getNode().split(":");
        System.out.println(hostAndPort);
        return new Jedis(hostAndPort[0], Integer.parseInt(hostAndPort[1]), redisSingleProperties.getCommandTimeout(), false);
    }
}
