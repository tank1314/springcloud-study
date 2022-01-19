package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.BaseSpringBootTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class RankControllerTest extends BaseSpringBootTest {

    private Random random;
    private RestTemplate restTemplate;

    @Before
    public void init() {
        random = new Random();
        restTemplate = new RestTemplate();
    }

    public Integer getUserId() {
        return random.nextInt(1024);
    }

    public Double getScore() {
        return random.nextDouble() * 1024;
    }

    @Test
    public void testInitRank() {
        for (int i = 0; i < 20; i++) {
            String result = restTemplate.getForObject("http://127.0.0.1:4300/rankUpdate?userId=" + getUserId() + "&score=" + getScore(), String.class);
            System.out.println(result);
        }
    }

}
