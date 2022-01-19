package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.annonation.SemaphoreLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class LimitController {

    @SemaphoreLimit(limitKey = "limit01", value = 2)
    @RequestMapping("/limit01")
    public Object limit01() {
        System.out.println("++++++++++request params success ++++++++++");
        /*try {
            //TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return System.currentTimeMillis() + "limit01 receive request params";
    }


    @SemaphoreLimit(limitKey = "limit02", value = 20)
    @RequestMapping("/limit02")
    public Object limit02() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return System.currentTimeMillis() + " limit02 receive request params";
    }


}
