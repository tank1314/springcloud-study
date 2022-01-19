package com.tank.springcloud.springbootclient.commond;

import java.io.Serializable;

/**
 * 积分排行榜
 */
public class RankVo implements Serializable {

    private Long userId;
    private Long score;
    private Long rank;

    public RankVo(Long userId, Long score, Long rank) {
        this.userId = userId;
        this.score = score;
        this.rank = rank;
    }

    public RankVo() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
}
