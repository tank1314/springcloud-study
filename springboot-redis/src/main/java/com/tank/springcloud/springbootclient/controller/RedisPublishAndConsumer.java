package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.serivce.RedisPubAndSub;
import com.tank.springcloud.springbootclient.util.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用redis实现消息的发布订阅
 * <p>
 * 使用说明：
 * 1、只有在线的消费者能接收到消息；
 * 2、消费者一个消息只能拿到一次
 * 场景说明：
 * 1、利用redis+内存做二级缓存：对于一个应用部署多台服务器，期望同时失效所有服务器的内存缓存
 * 2、使用redis实现配置动态刷新
 * 3、订阅过期key 失效事件
 */
@RestController
public class RedisPublishAndConsumer {

    protected static final Logger logger = LoggerFactory.getLogger(RedisPublishAndConsumer.class);

    @Autowired
    private RedisPubAndSub redisPubAndSub;

    @RequestMapping("/pub")
    public String pub(String key, String value) {
        redisPubAndSub.publish(key, value);
        return key + "---publish---success";
    }
}
