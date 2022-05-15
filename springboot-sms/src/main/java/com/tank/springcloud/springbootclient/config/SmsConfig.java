package com.tank.springcloud.springbootclient.config;

import com.tank.springcloud.springbootclient.enums.SmsSendRejectStrategyEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsConfig {
    private Map<Integer, String> templates;

    private SmsSendRejectStrategyEnum defaultRejectStrategyEnum;
}
