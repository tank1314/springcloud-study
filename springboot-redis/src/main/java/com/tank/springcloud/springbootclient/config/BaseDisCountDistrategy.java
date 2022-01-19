package com.tank.springcloud.springbootclient.config;

import com.tank.springcloud.springbootclient.factory.BaseDisCountStrategyFactory;
import com.tank.springcloud.springbootclient.interfaces.DisCountStrategy;
import org.springframework.beans.factory.InitializingBean;

/**
 * 创建抽象基类，用于实现策略接口
 * 将会员等级及对应的策略实现类注册到map中
 */
public abstract class BaseDisCountDistrategy implements DisCountStrategy, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        BaseDisCountStrategyFactory.registry(getLevel(), this);
    }


}
