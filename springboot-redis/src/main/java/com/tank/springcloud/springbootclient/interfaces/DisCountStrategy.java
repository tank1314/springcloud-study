package com.tank.springcloud.springbootclient.interfaces;

/**
 * 根据会员的不同等级享受不同的优惠折扣
 */
public interface DisCountStrategy {

    /**
     * 会员等级
     *
     * @return
     */
    int getLevel();

    /**
     * 根据会员等级即使出折扣情况
     *
     * @param level
     * @return
     */
    int discount(int level);
}
