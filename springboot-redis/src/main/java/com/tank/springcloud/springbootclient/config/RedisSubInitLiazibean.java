package com.tank.springcloud.springbootclient.config;

import com.tank.springcloud.springbootclient.serivce.RedisPubAndSub;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Configuration
public class RedisSubInitLiazibean implements InitializingBean {
    @Autowired
    private RedisPubAndSub redisPubAndSub;

    public void afterPropertiesSet() throws Exception {
        System.out.println("application init start ....");
        redisPubAndSub.subScribe(new MessageListener() {
            public void onMessage(Message message, byte[] pattern) {
                System.out.println("receive message is " + message);
            }
        }, "pub-sit02");
    }
}
