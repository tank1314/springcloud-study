package com.tank.springcloud.springbootclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 集成redis哨兵
 * springboot2之前redis的连接池为jedis，2.0以后redis的连接池改为了lettuce，lettuce能够支持redis4，需要java8及以上。lettuce是基于netty实现的与redis进行同步和异步的通信
 * Jedis：Redis的Java实现客户端，比较全面的提供了Redis的操作特性。在多个线程间共享一个 Jedis 实例时是线程不安全的,需要使用连接池
 * Lettuce：高级Redis客户端，用于线程安全同步，异步和响应使用，支持集群，主要在一些分布式缓存框架上使用比较多
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
public class SpringbootRedisClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisClientApplication.class, args);
    }

}
