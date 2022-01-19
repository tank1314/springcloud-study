package com.tank.springcloud.springbootclient.config;

import com.tank.springcloud.springbootclient.util.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisAutoConfig {

    @Bean
    public RedisClient redisClient(StringRedisTemplate redisTemplate) {
        RedisClient clientCache = new RedisClient();
        clientCache.setRedisTemplate(redisTemplate);
        return clientCache;
    }
}
