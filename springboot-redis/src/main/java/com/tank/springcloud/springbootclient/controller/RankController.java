package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.commond.RankVo;
import com.tank.springcloud.springbootclient.config.RedisAutoConfig;
import com.tank.springcloud.springbootclient.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 使用Redis 开发排行榜功能
 */
@RestController
public class RankController {

    @Autowired
    private RedisClient redisClient;


    /**
     * 上传用户积分信息，并获取排名信息
     * 因为zset默认积分小的在前面，所以我们对score进行取反，这样用户的积分越大，对应的score越小，排名越高
     *
     * @param userId
     * @param score
     * @return
     */
    @RequestMapping("/rankUpdate")
    public String updateRank(Long userId, Float score) {
        redisClient.addZset("rank", String.valueOf(userId), -score);
        Long rank = redisClient.rank("rank", String.valueOf(userId));
        return userId + " current rank is " + rank;
    }

    /**
     * 获取自己最新排行及得分情况
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getRank")
    public RankVo getRank(Long userId) {
        Long rank = redisClient.rank("rank", String.valueOf(userId));
        if (null == rank) {
            return new RankVo(userId, 0L, 0L);
        }
        Double score = redisClient.score("rank", String.valueOf(userId));
        System.out.println(score.longValue());
        return new RankVo(userId, score.longValue(), rank + 1);
    }

    private List<RankVo> buildRedisRankToBZ(Set<ZSetOperations.TypedTuple<String>> result, long offset) {
        List<RankVo> rankList = new ArrayList<>(result.size());
        long rank = offset;
        for (ZSetOperations.TypedTuple<String> sub : result) {
            rankList.add(new RankVo(Long.parseLong(sub.getValue()), sub.getScore().longValue(), rank++));
        }
        return rankList;
    }

    /**
     * 获取前N 积分排行榜
     *
     * @param n
     * @return
     */
    @RequestMapping("/getRankN")
    public List<RankVo> getTopRank(int n) {
        Set<ZSetOperations.TypedTuple<String>> rank = redisClient.rangeWithScore("rank", 1, n - 1);
        System.out.println(rank);
        return buildRedisRankToBZ(rank, 1);
    }
}
