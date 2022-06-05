package com.tank.springcloud.springbootclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.encrypt")
@Data
public class EncryptProperties {
    private final static String DEFAULT_KEY = "good_good_study1";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
