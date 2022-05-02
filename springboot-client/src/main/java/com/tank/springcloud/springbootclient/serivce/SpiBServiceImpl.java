package com.tank.springcloud.springbootclient.serivce;

import org.springframework.stereotype.Service;

//@Service("spiBservic")
public class SpiBServiceImpl implements SpiService{
    @Override
        public void mockAdd() {
        System.out.println("abbbbbbbbbb 触发");
        }
}
