package com.tank.springcloud.springbootclient.util.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadUtil3 implements Runnable {

    ReadWriteLock lock = new ReentrantReadWriteLock();
    int num = 0;

    @Override
    public void run() {
        lock.readLock().lock();
        for (int i = 0; i < 5; i++) {
            num++;
            System.out.println(Thread.currentThread().getName() + "===" + num);
        }
        lock.readLock().unlock();
    }
}
