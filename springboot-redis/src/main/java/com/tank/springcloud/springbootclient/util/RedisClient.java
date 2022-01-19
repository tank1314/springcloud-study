package com.tank.springcloud.springbootclient.util;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisClient {

    private StringRedisTemplate redisTemplate;

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 普通的消息存储
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key, value);
    }

    /**
     * 带消息过期时间
     *
     * @param key
     * @param value
     * @param expireSecond
     * @return
     */
    public Boolean set(String key, String value, long expireSecond) {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key, value);
        return redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS);
    }

    public String get(String key) {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        String s = stringStringValueOperations.get(key);
        return s;
    }

    /**
     * 验证key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 新增元素
     * 新增元素时，如果元素存在，则用心的score来替换原来的，返回0 ；
     * 如果元素不存在，则新增一个
     *
     * @param key
     * @param value
     * @param score
     */
    public void addZset(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 删除元素
     *
     * @param key
     * @param value
     */
    public void removeZset(String key, String value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 修改score
     * zset中的元素塞入后，可以修改其score的值，通过zincrby来对score进行加减；
     * 当元素不存在时，则会新插入一个
     * zincrby 和zadd 区别在于 前者增量修改，后者覆盖score的方式
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Double incrScore(String key, String value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 获取value对应的score
     *
     * @param key
     * @param value
     * @return
     */
    public Double score(String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 获取value在集合中的排名
     * score越小排名越高
     *
     * @param key
     * @param value
     * @return
     */
    public long rank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 获取集合中的数据【因为为有序，所以可以获取指定范围内的数据】
     * 根据排序位置获取数据
     * 根据score区间获取排序位置
     * 查询集合中指定顺序的值，0 -1 表示获取全部集合的内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> range(String key, int start, int end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值和score,0 ,-1表示获取全部内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScore(String key, int start, int end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值，
     * 返回有序的集合中，score大的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> revRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 根据score的值，来获取满足条件的集合
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> sortRange(String key, int min, int max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }


    /**
     * list数组元素添加
     * 从左往右添加或者从右往左
     *
     * @param key
     * @param value
     */
    public void addList(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取指定索引位置的值，index为-1 时，表示返回的是最后一个，当index大于实际列表长度时，返回null
     *
     * @param key
     * @param index
     * @return
     */
    public String getListIndex(String key, int index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取范围值，闭区间，start和end这两个下标值都会返回，end为-1，表示获取的是最后一个
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> rangList(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取列表的长度
     *
     * @param key
     * @return
     */
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 设置list中指定下标的值，采用的是替换原则
     * 修改
     *
     * @param key
     * @param index
     * @param value
     */
    public void setList(String key, Integer index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 删除列表中值为value的元素，总共删除count 次
     *
     * @param key
     * @param value
     * @param count
     */
    public void removeList(String key, String value, int count) {
        redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 删除list的首尾，保留【start,end】之间的值
     *
     * @param key
     * @param start
     * @param end
     */
    public void trim(String key, Integer start, Integer end) {
        redisTemplate.opsForList().trim(key, start, end);
    }


    /**
     * List使用场景多用作 队列或者堆栈使用
     */

    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    public void lpush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取元素
     * 获取指定索引位置的值，index为-1时，表示返回最后一个，当index大于实际的列表长度时，返回null
     * <p>
     * 与jdk中list获取某个索引value的不同点：这里的index可以为负数，-1表示最右边一个，-2表示最右边第二个
     *
     * @param key
     * @param index
     * @return
     */
    public String index(String key, int index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取范围值，闭区间，start和end这两个下标的值都会返回，end为-1，表示获取最后一个
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> rangeList(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取列表的长度
     *
     * @param key
     * @return
     */
    public long listSize(String key) {
        return redisTemplate.opsForList().size(key);

    }


    /**
     * 添加geo信息
     *
     * @param key       缓存key
     * @param longitude 经度
     * @param latitude  纬度
     * @param member    位置名
     */
    public void addGeo(String key, double longitude, double latitude, String member) {
        redisTemplate.opsForGeo().add(key, new Point(longitude, latitude), member);
    }

    /**
     * 获取地方的坐标
     *
     * @param key
     * @param member
     * @return
     */
    public List<Point> getGeo(String key, String... member) {
        List<Point> position = this.redisTemplate.opsForGeo().position(key, member);
        return position;
    }

    /**
     * 判断两地距离 默认单位：米
     *
     * @param key
     * @param sourece
     * @param dest
     * @return
     */
    public Distance distance(String key, String sourece, String dest) {
        return this.redisTemplate.opsForGeo().distance(key, sourece, dest);
    }

    /**
     * 以给定的经纬度为中心，返回与中心距离不超过给定最大距离的所有位置元素
     *
     * @param key
     * @param longitude
     * @param latitude
     */
    public void nearGeo(String key, double longitude, double latitude) {
        Circle circle = new Circle(longitude, latitude, Metrics.KILOMETERS.getMultiplier() * 5);
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = this.redisTemplate.opsForGeo().radius(key, circle, geoRadiusCommandArgs);
    }

    /**
     * 根据位置获取临近元素
     *
     * @param key
     * @param member
     */
    public void nearByPlace(String key, String member) {
        Distance distance = new Distance(5, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs limit = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(5);

        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = this.redisTemplate.opsForGeo().radius(key, member, distance, limit);
    }

    /**
     * 将二维的经纬度转换成字符串。
     * @param key
     * @param addresses
     * @return
     */
    public List<String> geoHash(String key, String... addresses) {
        List<String> hash = redisTemplate.opsForGeo().hash(key, addresses);
        return hash;
    }


}
