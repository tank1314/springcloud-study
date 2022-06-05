package com.tank.springcloud.springbootclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 微服务架构特点就是多服务，多数据源，支撑系统应用。
 * 这样导致微服务之间存在依赖关系。如果其中一个服务故障，可能导致系统宕机，这就是所谓的雪崩效应。
 * •@EnableHystrix 启动类注解控制熔断功能。
 * •@HystrixCommand 方法注解，熔断控制配置。
 */
@SpringBootApplication

public class SpringbootEncryptClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEncryptClientApplication.class, args);
    }

}
