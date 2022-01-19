package com.tank.springcloud.springbootclient.util.thread;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadUtil2 implements Runnable {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    int num = 0;

    @Override
    public void run() {
        //lock.writeLock().lock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        for (int i = 0; i < 5; i++) {
            num++;
            System.out.println(Thread.currentThread().getName() + "===" + num);
        }
        writeLock.unlock();
    }
}
