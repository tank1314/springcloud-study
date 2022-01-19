package com.tank.springcloud.springbootclient.serivce;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "springboot-server1")
// 熔断方式，类级别熔断优先于方法级别
//@FeignClient(value = "springboot-server1", fallback = FallBackService.class)
public interface ServerClient {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
