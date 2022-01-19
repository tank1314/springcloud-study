package com.tank.springcloud.springbootclient.commond;

import java.io.Serializable;

public class VisitVO implements Serializable {
    private Long pv ;//url的总访问次数，每个ip每天访问次数+1
    private Long uv ;//页面总的ip访问数
    private Long rank ; //当前ip,第一次访问本url的排名
    private Long hot ;//热度，每次访问计数+1

    public VisitVO(Long pv, Long uv, Long rank, Long hot) {
        this.pv = pv;
        this.uv = uv;
        this.rank = rank;
        this.hot = hot;
    }

    public VisitVO(VisitVO vo) {
        this.pv = vo.pv;
        this.uv = vo.uv;
        this.rank = vo.rank;
        this.hot = vo.hot;
    }

    public VisitVO() {
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getHot() {
        return hot;
    }

    public void setHot(Long hot) {
        this.hot = hot;
    }
}
