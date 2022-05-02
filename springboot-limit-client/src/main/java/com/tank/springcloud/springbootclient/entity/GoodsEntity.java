package com.tank.springcloud.springbootclient.entity;

import java.io.Serializable;

public class GoodsEntity {
    //private static final long serialVersionUID = 3636584634781009739L;

    private String goodsId ;
    private String goodsName;
    private String goodsDesc;

    public GoodsEntity() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public GoodsEntity(String goodsId, String goodsName, String goodsDesc) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
    }
}
