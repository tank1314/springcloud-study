package com.tank.springcloud.springbootclient.serivce;

import org.springframework.stereotype.Service;

//@Service("spiAService")
public class SpiAServiceImpl implements SpiService{
    @Override
    public void mockAdd() {
        System.out.println("spi A 被触发....");
    }

    public void threadTool(){
        int a = 90,b = 20 ;
        for(int i = 0 ;i< 10 ;i++){
            int c = a+b+i ;
            System.out.println(c);
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread begin ");
            }
        });
    }
}
