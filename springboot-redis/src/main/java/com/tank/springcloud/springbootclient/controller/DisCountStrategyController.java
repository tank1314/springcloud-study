package com.tank.springcloud.springbootclient.controller;

import com.tank.springcloud.springbootclient.factory.BaseDisCountStrategyFactory;
import com.tank.springcloud.springbootclient.interfaces.DisCountStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员折扣率测试
 */
@RestController
public class DisCountStrategyController {
    protected static final Logger logger = LoggerFactory.getLogger(DisCountStrategyController.class);


    @RequestMapping("/level")
    public Object getDiscount(int level) {
        DisCountStrategy disCountByLevel = BaseDisCountStrategyFactory.getDisCountByLevel(level);
        int disCount = disCountByLevel.discount(level);
        return "" + disCountByLevel.discount(level);
    }
}
