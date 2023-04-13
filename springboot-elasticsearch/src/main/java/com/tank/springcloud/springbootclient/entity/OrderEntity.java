package com.tank.springcloud.springbootclient.entity;

import java.io.Serializable;

public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 3636584634781009739L;

    private String id ;
    private String orderNo;
    private String buyer;
    private String goodsId ;

    public OrderEntity() {
    }

    public OrderEntity(String id, String orderNo, String buyer, String goodsId) {
        this.id = id;
        this.orderNo = orderNo;
        this.buyer = buyer;
        this.goodsId = goodsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
