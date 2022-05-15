package com.tank.springcloud.springbootclient.util;

import com.tank.springcloud.springbootclient.entity.GoodsEntity;
import com.tank.springcloud.springbootclient.entity.OrderEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListStatus {

    public static void main(String[] args) {
        /*String strs = "start,process,end";
        List<String> strings = Arrays.asList(strs.split(","));
        String process = strings.get(strings.indexOf("process")+1);
        System.out.println(process);*/
        List<OrderEntity> orderList = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity("10", "20", "30", "40");
        orderList.add(orderEntity);
        Map<String, List> init = init();
        List orders = init.get("order");
        orderList.addAll(orders);
        System.out.println(orderList.size());
        orderList.stream().forEach(a -> {
            System.out.println(a.getOrderNo());
        });

    }

    public static Map<String, List> init() {
        Map<String, List> resultMap = new ConcurrentHashMap<>();
        List<OrderEntity> orderList = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity("1", "2", "3", "4");
        orderList.add(orderEntity);
        resultMap.put("order", orderList);

        List<GoodsEntity> goodsList = new ArrayList<>();
        GoodsEntity good = new GoodsEntity("1", "2", "4");
        goodsList.add(good);
        resultMap.put("good", goodsList);
        return resultMap;
    }
}
