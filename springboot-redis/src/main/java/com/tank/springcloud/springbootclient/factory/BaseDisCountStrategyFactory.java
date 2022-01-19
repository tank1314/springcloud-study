package com.tank.springcloud.springbootclient.factory;

import com.tank.springcloud.springbootclient.interfaces.DisCountStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建会员折扣基类
 * 将折扣策略和会员等级进行注册
 */
public class BaseDisCountStrategyFactory {

    private static final Logger logger = LoggerFactory.getLogger(BaseDisCountStrategyFactory.class);

    private static final Map<Integer, DisCountStrategy> disCountMap = new ConcurrentHashMap<>();

    public static void registry(Integer level, DisCountStrategy disCountStrategy) {
        logger.info("level{} discountStrategy{}", level, disCountStrategy);
        disCountMap.put(level, disCountStrategy);
    }

    public static DisCountStrategy getDisCountByLevel(Integer level) {
        return disCountMap.get(level);
    }
}
