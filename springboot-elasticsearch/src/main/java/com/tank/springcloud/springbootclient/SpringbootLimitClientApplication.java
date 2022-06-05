package com.tank.springcloud.springbootclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
//@EnableEurekaClient
public class SpringbootLimitClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLimitClientApplication.class, args);
    }

}
