package com.tank.springcloud.springbootclient.serivce;

import com.tank.springcloud.springbootclient.config.BaseDisCountDistrategy;
import com.tank.springcloud.springbootclient.factory.BaseDisCountStrategyFactory;
import org.springframework.stereotype.Service;

/**
 * 高级会员折扣率
 */
@Service
public class HighLevelDisCountStrategy extends BaseDisCountDistrategy {

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int discount(int level) {
        return 6;
    }
}
