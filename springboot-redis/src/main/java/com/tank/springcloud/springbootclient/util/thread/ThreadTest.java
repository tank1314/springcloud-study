package com.tank.springcloud.springbootclient.util.thread;

import java.util.Random;

public class ThreadTest {
    public static void main(String[] args) {

        // 一个线程等待另外一个线程执行完才进行
        /*ThreadUtil t1 = new ThreadUtil();
        ThreadUtil t2 = new ThreadUtil();
        Thread t_1 = new Thread(t1, "demo1");
        Thread t_2 = new Thread(t2, "demo2");
        t_1.start();
        t_2.start();*/

        //一个线程等待另外线程执行完成后继续
        /*ThreadUtil2 t3 = new ThreadUtil2();
        Thread t_3 = new Thread(t3, "demo3");
        Thread t_4 = new Thread(t3, "demo4");
        t_3.start();
        t_4.start();*/


        //lock.readLock() 线程不阻塞
       /* ThreadUtil3 t4 = new ThreadUtil3();
        Thread t_5 = new Thread(t4, "demo05");
        Thread t_6 = new Thread(t4, "demo06");
        t_5.start();
        t_6.start();*/


        LockUtil lockUtil = new LockUtil();

        /*for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lockUtil.setLock(new Random().nextInt(100));
            }, "writeLock_" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lockUtil.getLock();
            }, "readLock_" + i).start();
        }*/

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String result = lockUtil.getCache("dbkey");
                System.out.println(Thread.currentThread().getName() + "返回结果集：" + result);
            }, "cache_" + i).start();
        }
       /* String result = lockUtil.getCache("dbkey");
        String result2 = lockUtil.getCache("dbkey");
        System.out.println(result+"---"+result2);*/


    }
}
