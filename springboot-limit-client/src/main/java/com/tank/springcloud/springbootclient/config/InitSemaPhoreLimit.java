package com.tank.springcloud.springbootclient.config;

import com.tank.springcloud.springbootclient.annonation.SemaphoreLimit;
import com.tank.springcloud.springbootclient.aop.SemaphoreLimitAop;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * 初始化所以限流key及初始化对应的信号量值
 */
@Component
public class InitSemaPhoreLimit implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RestController.class);
        for (Map.Entry<String, Object> map : beansWithAnnotation.entrySet()) {
            Class<?> cls = map.getValue().getClass();
            Method[] declaredMethods = cls.getSuperclass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                System.out.println(method.getName());
                if (method.isAnnotationPresent(SemaphoreLimit.class)) {
                    String limitKey = method.getAnnotation(SemaphoreLimit.class).limitKey();
                    int value = method.getAnnotation(SemaphoreLimit.class).value();
                    System.out.println("limitKey" + limitKey + "----value--" + value);
                    SemaphoreLimitAop.semaphoreMap.put(limitKey, new Semaphore(value));
                }
            }
        }
    }
}
