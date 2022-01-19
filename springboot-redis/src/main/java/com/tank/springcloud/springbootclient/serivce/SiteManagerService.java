package com.tank.springcloud.springbootclient.serivce;

import com.baozun.utilities.json.JsonUtil;
import com.tank.springcloud.springbootclient.commond.SiteVisitDTO;
import com.tank.springcloud.springbootclient.commond.VisitReqDTO;
import com.tank.springcloud.springbootclient.commond.VisitVO;
import com.tank.springcloud.springbootclient.util.RedisClient;
import com.tank.springcloud.springbootclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Set;

@Service
public class SiteManagerService {

    @Autowired
    private RedisClient redisClient;

    /**
     * 应用的热度统计计数
     *
     * @param app
     * @return
     */
    public String buildHotKey(String app) {
        return "hot:" + app;
    }

    /**
     * 热度，每访问一次，计数+1
     *
     * @param key
     * @param uri
     * @return
     */
    public Long addHot(String key, String uri) {

        return redisClient.getRedisTemplate().execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hIncrBy(key.getBytes(), uri.getBytes(), 1);
            }
        });
    }

    /**
     * 应用pv统计计数
     *
     * @param app
     * @return
     */
    public String buildPvKey(String app) {
        return "site:" + app;
    }

    /**
     * 获取pv
     *
     * @param key
     * @param uri
     * @return
     */
    public Long getPv(String key, String uri) {
        //return redisClient
        return redisClient.getRedisTemplate().execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bytes = connection.hGet(key.getBytes(), uri.getBytes());
                if (bytes == null || bytes.length == 0) {
                    return null;
                }
                return Long.parseLong(new String(bytes));
            }
        });
    }

    /**
     * pv次数+1
     *
     * @param key
     * @param uri
     */
    public void addPv(String key, String uri) {
        redisClient.getRedisTemplate().execute(new RedisCallback<Void>() {
            public Void doInRedis(RedisConnection connection) throws DataAccessException {
                connection.hIncrBy(key.getBytes(), uri.getBytes(), 1);
                return null;
            }
        });
    }

    /**
     * app+uri 对应uv
     *
     * @param app
     * @param uri
     * @return
     */
    public String buildVuKey(String app, String uri) {
        return "uri_rank:" + app + ":" + uri;
    }

    /**
     * 获取uri对应的uv,以及当前访问ip的历史访问排行
     * 使用zset存储，key为uri的唯一标识，value为ip,score为访问的排名
     *
     * @param key
     * @param ip
     * @return
     */
    public ImmutablePair<Long, Long> getUv(String key, String ip) {
        Long uv = redisClient.getRedisTemplate().execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Set<RedisZSetCommands.Tuple> tuples = connection.zRangeWithScores(key.getBytes(), -1, -1);
                if (CollectionUtils.isEmpty(tuples)) {
                    return 0L;
                }
                Double score = tuples.stream().findFirst().get().getScore();
                return score.longValue();
            }
        });
        //此时表示无人访问
        if (uv == null || uv == 0L) {
            return ImmutablePair.of(0L, 0L);
        }
        Long rank = redisClient.getRedisTemplate().execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Double aDouble = connection.zScore(key.getBytes(), ip.getBytes());
                return aDouble == null ? 0L : aDouble.longValue();
            }
        });
        return ImmutablePair.of(uv, rank);
    }

    /**
     * uv+1
     *
     * @param key
     * @param ip
     * @param rank
     */
    public void addUv(String key, String ip, Long rank) {
        redisClient.getRedisTemplate().execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection connection) throws DataAccessException {
                connection.zAdd(key.getBytes(), rank, ip.getBytes());
                return null;
            }
        });
    }

    /**
     * 获取当天日期
     *
     * @return
     */
    public String getToday() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        StringBuilder builder = new StringBuilder(8);

        return builder.append(year).append(month < 10 ? "0" : "").append(month).append(day < 10 ? "0" : "").append(day).toString();
    }

    /**
     * 每日访问统计
     *
     * @param app
     * @param uri
     * @return
     */
    public String buildUriTagKey(String app, String uri) {
        return "uri_tag:" + getToday() + ":" + app + ":" + uri;
    }


    public boolean visitToday(String key, String ip) {
        String[] ips = StringUtils.split(ip, ".");
        for (int i = 0; i < ips.length; i++) {
            if (!contain(key + "_" + i, Integer.valueOf(ips[i]))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 标记ip是否被访问过
     *
     * @param key
     * @param ip
     */
    public void tagVisit(String key, String ip) {
        String[] ips = StringUtils.split(ip, ".");
        for (int i = 0; i < ips.length; i++) {
            int finalI = i;
            Void execute = redisClient.getRedisTemplate().execute(new RedisCallback<Void>() {
                public Void doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.setBit((key + "_" + finalI).getBytes(), Integer.valueOf(ips[finalI]), true);
                    return null;
                }
            });
        }
    }

    public boolean contain(String key, Integer val) {
        return redisClient.getRedisTemplate().execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.getBit(key.getBytes(), val);
            }
        });
    }

    private VisitVO doVisit(String app, String uri, String ip) {
        String pvKey = buildPvKey(app);
        String hotKey = buildHotKey(app);
        String uvKey = buildVuKey(app, uri);
        String todayVisitKey = buildUriTagKey(app, uri);

        Long aLong = addHot(hotKey, uri);
        //获取pv数据
        Long pv = getPv(pvKey, uri);
        if (pv == null || pv == 0) {
            addPv(pvKey, uri);
            addUv(uvKey, uri, 1L);
            tagVisit(todayVisitKey, ip);
            return new VisitVO(1L, 1L, 1L, aLong);
        }
        boolean visitToday = visitToday(todayVisitKey, ip);
        ImmutablePair<Long, Long> uv = getUv(uvKey, ip);
        //判断ip今天是否访问过，若是，则不进行修改pv/uv,直接返回相关数据
        if (visitToday) {
            return new VisitVO(pv, uv.getLeft(), uv.getRight(), aLong);
        }
        //首次访问pv+1,uv+1
        if (uv.left == 0L) {
            addUv(uvKey, uri, 1L);
            addPv(pvKey, uri);
            tagVisit(todayVisitKey, ip);
            return new VisitVO(pv + 1, 1L, 1L, aLong);
        }
        // ip首次访问，pv+1,uv+1
        else if (uv.right == 0L) {
            addPv(pvKey, uri);
            addUv(uvKey, ip, uv.left + 1);
            tagVisit(todayVisitKey, ip);
            return new VisitVO(pv + 1, uv.left + 1, uv.left + 1, aLong);
        }
        //ip今天第一次访问，pv+1,uv不变
        else {
            addPv(pvKey, uri);
            tagVisit(todayVisitKey, ip);
            return new VisitVO(pv + 1, uv.left, uv.right, aLong);
        }
    }

    /**
     * uri 访问统计
     *
     * @param reqDTO
     * @return
     */
    public SiteVisitDTO visit(VisitReqDTO reqDTO) {
        ImmutablePair<String, String> uri = URIUtil.formtUri(reqDTO.getUri());
        VisitVO uriVisitVO = doVisit(reqDTO.getApp(), uri.getRight(), reqDTO.getIp());
        VisitVO siteVisit;
        if (uri.getLeft().equals(uri.getRight())) {
            siteVisit = new VisitVO(uriVisitVO);
        } else {
            siteVisit = doVisit(reqDTO.getApp(), uri.getLeft(), reqDTO.getIp());
        }
        System.out.println(JsonUtil.writeValue(siteVisit) + "---" + JsonUtil.writeValue(uriVisitVO));
        return new SiteVisitDTO(siteVisit, uriVisitVO);
    }
}
