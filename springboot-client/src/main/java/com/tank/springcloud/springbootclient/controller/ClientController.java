package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.serivce.LimitClientService;
import com.tank.springcloud.springbootclient.serivce.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class ClientController {

    @Autowired
    private ServerClient client;

    @Autowired
    private LimitClientService limitClientService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return client.add(20, 30);
    }

    /**
     * 模拟并发进行验证测试
     *
     * @return
     */
    @RequestMapping(value = "limit")
    public Object limit() {
        System.out.println("1111111111111");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                String result = limitClientService.limit01();
                System.out.println("-----" + result);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
       // return limitClientService.limit01();
        return "success";
    }
}
