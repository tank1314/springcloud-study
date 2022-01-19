package com.tank.springcloud.springbootclient.controller;

import com.baozun.utilities.json.JsonUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tank.springcloud.springbootclient.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RedisController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/redis")
    public String index() {
        redisClient.set("demo", System.currentTimeMillis() + "");
        return "redis 插入成功";
    }

    @RequestMapping("/redis-expire")
    public String index2() {
        redisClient.set("demo", System.currentTimeMillis() + "", 10);
        return "redis 带过期时间 插入成功";
    }

    /**
     * zset redis
     *
     * @return
     */
    @RequestMapping("/zsetAdd")
    public String zsetIndex() {
        for (int i = 0; i < 100; i++) {
            redisClient.addZset("demo-sit:1", System.currentTimeMillis() + "-" + i, i);
        }
        return "";
    }

    @RequestMapping("/getzsetValue")
    public String zsetGetIndex() {
        double score = redisClient.score("zset::demo", "1640228714641-7");
        return "zset::demo - 1640228714641-7 current score value" + score;
    }

    @RequestMapping("/getZsetAll")
    public String range() {
        Set<String> result = redisClient.range("demo-sit", 0, -1);
        System.out.println(result);
        String resultStr = JsonUtil.writeValue(result);
        return resultStr;
    }

}
