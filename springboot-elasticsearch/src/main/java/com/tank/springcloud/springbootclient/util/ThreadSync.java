package com.tank.springcloud.springbootclient.util;

import com.tank.springcloud.springbootclient.entity.OrderEntity;

/**
 * 主子线程数据同步
 */
public class ThreadSync {
    static OrderEntity order = new OrderEntity();
    public static void main(String[] args) {

       new Thread(()->{
           order.setOrderNo("10000");
           System.out.println("开启主线程");
           childrenThread(1);
       }).start();
    }

    public static void childrenThread(int i){
        new Thread(()->{
            System.out.println("子线程任务开启"+i+order.getOrderNo());
        }).start();
    }
}
