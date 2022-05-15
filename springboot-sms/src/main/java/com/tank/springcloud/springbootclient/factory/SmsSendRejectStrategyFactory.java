package com.tank.springcloud.springbootclient.factory;

import com.tank.springcloud.springbootclient.enums.SmsSendRejectStrategyEnum;
import com.tank.springcloud.springbootclient.strategy.SmsSendAnyMatchStrategy;
import com.tank.springcloud.springbootclient.strategy.SmsSendRejectStrategy;
import com.tank.springcloud.springbootclient.strategy.SmsSendTotallyMatchStrategy;

/**
 * 拒绝策略工厂
 */
public class SmsSendRejectStrategyFactory {
    //private static final SmsSendIgnoreStrategy IGNORE_STRATEGY = new SmsSendIgnoreStrategy();

    private static final SmsSendAnyMatchStrategy ANY_MATCH_STRATEGY = new SmsSendAnyMatchStrategy();

    private static final SmsSendTotallyMatchStrategy TOTALLY_MATCH_STRATEGY = new SmsSendTotallyMatchStrategy();

    public static SmsSendRejectStrategy getStrategy(SmsSendRejectStrategyEnum strategyEnum) {
        switch (strategyEnum) {
//            case IGNORE:
//                return IGNORE_STRATEGY;
            case ANY_MATCH:
                return ANY_MATCH_STRATEGY;
            case TOTALLY_MATCH:
                return TOTALLY_MATCH_STRATEGY;
            default:
                throw new IllegalArgumentException("Illegal StrategyEnum Param");
        }
    }
}
