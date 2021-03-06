package com.tank.springcloud.springbootclient;

import com.tank.springcloud.springbootclient.entity.OrderEntity;
import com.tank.springcloud.springbootclient.enums.OrderEunm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 限流框架：
 * 限流算法：漏桶算法和令牌桶
 * 对接口限流的目的是通过对并发访问、请求进行限速，
 * 或者对一个时间窗口内的请求进行限速进而保护系统，
 * 一旦达到限定速率则可以拒绝服务、排队和等待
 */
@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
//@EnableEurekaClient
public class SpringbootLimitClientApplication {

    public static void main(String[] args) {
        Map<OrderEunm, List<OrderEntity>> map = new HashMap<>();
        List<OrderEntity> list = new ArrayList<>() ;
        OrderEntity dataRow = new OrderEntity();
        dataRow.setBuyer("1111");
        dataRow.setGoodsId("2222");
        dataRow.setOrderNo("3333");
        list.add(dataRow);
        map.put(OrderEunm.SIGN,list);

        SpringApplication.run(SpringbootLimitClientApplication.class, args);
    }

}
