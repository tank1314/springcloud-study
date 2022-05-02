package com.tank.springcloud.springbootclient;

import com.tank.springcloud.springbootclient.serivce.SpiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Iterator;
import java.util.ServiceLoader;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringbootClientApplication {

    public static void main(String[] args) {
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iterator = load.iterator();
        while(iterator.hasNext()){
            SpiService next = iterator.next();
            next.mockAdd();
        }
        SpringApplication.run(SpringbootClientApplication.class, args);
    }

}
