package com.tank.springcloud.springbootclient.serivce;

import com.tank.springcloud.springbootclient.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;

/**
 * 消息发布订阅
 */
@Service
public class RedisPubAndSub {
    @Autowired
    private RedisClient redisClient;

    /**
     * 消息发布
     *
     * @param key
     * @param value
     */
    public void publish(String key, String value) {
        redisClient.getRedisTemplate().execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.publish(key.getBytes(), value.getBytes());
                return null;
            }
        });
    }

    /**
     * 消息订阅
     * @param messageListener
     * @param key
     */
    public void subScribe(MessageListener messageListener, String key) {
        redisClient.getRedisTemplate().execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.subscribe(messageListener, key.getBytes());
                return null;
            }
        });
    }
}
