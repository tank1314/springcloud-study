package com.tank.springcloud.springbootclient.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量工具类
 */
public class SemaphoreUtil {
    //最大并发数：5
    private final static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1);
                    System.out.println("currentThread" + Thread.currentThread().getName() + "---" + System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
    }
}
