package com.tank.springcloud.springbootclient.serivce;

import com.tank.springcloud.springbootclient.config.BaseDisCountDistrategy;
import org.springframework.stereotype.Service;

/**
 * 低会员等级享受的折扣率
 */
@Service
public class LowLevelDisCountStrategy extends BaseDisCountDistrategy {
    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public int discount(int level) {
        return 9;
    }
}
