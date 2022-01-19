package com.tank.springcloud.springbootclient.util.thread;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1、读锁可以被多个线程同时访问，但是这些线程在访问读锁的同时，其他线程不可以访问写锁
 * 2、写锁只能被一个线程访问，并与读锁形成互斥
 */
public class LockUtil {
    private Integer num;//共享数据，一个线程可操作write,但可多个线程读取

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();//读写锁

    private Map<String, String> cacheMap = new ConcurrentHashMap<>();

    /**
     * 适用于本地缓存
     *
     * @param key
     * @return
     */
    public String getCache(String key) {
        String result = null;
        readWriteLock.readLock().lock();
        result = cacheMap.get(key);
        if (StringUtils.isEmpty(result)) {
            readWriteLock.readLock().unlock();
            readWriteLock.writeLock().lock();
            try {
                result = cacheMap.get(key);
                if (StringUtils.isEmpty(result)) {
                    System.out.println(Thread.currentThread().getName() + "缓存中 未检索到数据，db插入数据...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cacheMap.put(key, "db cache load success");
                    System.out.println(Thread.currentThread().getName() + "DB 插入数据成功");
                }
            } finally {
                System.out.println(Thread.currentThread().getName()+" 写锁释放...");
                readWriteLock.writeLock().unlock();
            }
            System.out.println(Thread.currentThread().getName()+" 读锁加上锁...");
            readWriteLock.readLock().lock();
        }
        System.out.println(Thread.currentThread().getName()+" 读锁释放锁...");
        readWriteLock.readLock().unlock();
        return result;
    }


    public void getLock() {
        readWriteLock.readLock().lock();//加锁
        System.out.println(Thread.currentThread().getName() + "获取到读锁...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "释放读锁...当前值为" + num);
        readWriteLock.readLock().unlock();//解锁
    }

    public void setLock(Integer nums) {
        readWriteLock.writeLock().lock();//写锁上锁
        this.num = nums;
        System.out.println(Thread.currentThread().getName() + " 写上锁...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "写锁释放...当前写入值为" + num);
        readWriteLock.writeLock().unlock();//写锁释放
    }
}
