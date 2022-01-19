package com.tank.springcloud.springbootclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tank.springcloud.springbootclient.serivce.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ServerClient client;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return client.add(20, 40);
    }

    @RequestMapping("/show/add")
    @HystrixCommand(
            fallbackMethod = "getAddNum",
            commandProperties = {
                    //降级处理超时时间设置
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    // 任意时间点允许的最高并发数。超过该设置值后，拒绝执行请求。
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "1000"),
            },
            //配置执行线程池
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
            })
    public String addNum(@RequestParam Integer a, @RequestParam Integer b) {
        return client.add(a, b);
    }

    public String getAddNum(@RequestParam Integer a, @RequestParam Integer b) {
        return a + "-----" + b + "服务熔断...";
    }
}
