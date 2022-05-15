package com.tank.springcloud.springbootclient.enums;

/**
 * 短信发送拒绝策略枚举类
 */
public enum SmsSendRejectStrategyEnum {
    /**
     * 忽略,即全部发送
     */
    IGNORE,
    /**
     * 至少有一个匹配
     */
    ANY_MATCH,
    /**
     * 完全匹配
     */
    TOTALLY_MATCH;
}
