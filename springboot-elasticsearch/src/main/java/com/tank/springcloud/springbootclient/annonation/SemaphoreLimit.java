package com.tank.springcloud.springbootclient.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface SemaphoreLimit {
    public String limitKey() default "" ;//限流方法名称
    public int value() default 0 ;//可允许通过的请求数量
}
