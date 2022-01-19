package com.tank.springcloud.springbootclient.aop;

import com.tank.springcloud.springbootclient.annonation.SemaphoreLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Component
@Aspect
public class SemaphoreLimitAop {

    /**
     * 存储限流量和方法，必须是static 且线程安全。
     */
    /**
     * permits表示同一时间最多允许多少个线程同时执行acquire()和release()之间的代码，
     * 构造函数后边还可以跟上，Boolean值，true表示使用公平信号量，false使用非公平信号量
     * 公平信号量作用是：线程启动顺序与调用semaphore.acquire();的顺序有关，先启动的线程优先获得许可
     * 、new Semaphore(1);最多允许1个线程执行acquire()和release()之间的代码
     */
    public static Map<String, Semaphore> semaphoreMap = new ConcurrentHashMap<>();


    @Pointcut("@annotation(com.tank.springcloud.springbootclient.annonation.SemaphoreLimit)")
    public void serviceAspect() {
    }

    @Around("serviceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //获取目标对象
        Class<?> aClass = joinPoint.getTarget().getClass();
        //获取增强的方法信息
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        String limitKey = getLimitKey(aClass, name);
        Semaphore semaphore = semaphoreMap.get(limitKey);
        //非阻塞获取锁
        boolean flag = semaphore.tryAcquire();
        System.out.println("+++" + limitKey + "++++++result" + flag);
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            } else {
                obj = "request refused ";
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            semaphore.release();
        }
        return obj;
    }

    private String getLimitKey(Class<?> cls, String methodName) {
        for (Method method : cls.getDeclaredMethods()) {
            if (methodName.equals(method.getName())) {
                if (method.isAnnotationPresent(SemaphoreLimit.class)) {
                    return method.getAnnotation(SemaphoreLimit.class).limitKey();
                }
            }
        }
        return null;
    }
}
