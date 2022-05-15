package com.tank.springcloud.springbootclient;

import com.tank.springcloud.springbootclient.serivce.SmsService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SpringbootClientApplicationTests {
    @Resource
    private SmsService smsService;

    @Test
    public void testUtil(){
        System.out.println("111");
        smsService.send(null);
    }

    void contextLoads() {
    }

}
