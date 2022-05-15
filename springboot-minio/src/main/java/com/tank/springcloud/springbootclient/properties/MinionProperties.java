package com.tank.springcloud.springbootclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * minio配置参数
 */
@Data
@ConfigurationProperties(prefix = "minio")
@Component
public class MinionProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
