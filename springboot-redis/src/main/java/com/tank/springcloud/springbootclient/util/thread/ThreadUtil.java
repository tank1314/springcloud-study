package com.tank.springcloud.springbootclient.util.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadUtil implements Runnable {

    ReentrantLock lock = new ReentrantLock();
    int num = 0;

    public void run() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            num++;
            System.out.println(Thread.currentThread().getName() + "====" + num);
        }
        lock.unlock();
    }
}
