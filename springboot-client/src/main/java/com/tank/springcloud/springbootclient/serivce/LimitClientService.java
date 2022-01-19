package com.tank.springcloud.springbootclient.serivce;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "springboot-limit-client")
public interface LimitClientService {

    @RequestMapping("/limit01")
    public String limit01();

    @RequestMapping("/limit02")
    public String limit02();
}
