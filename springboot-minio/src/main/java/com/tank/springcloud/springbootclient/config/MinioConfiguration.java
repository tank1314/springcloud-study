package com.tank.springcloud.springbootclient.config;

import com.tank.springcloud.springbootclient.properties.MinionProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio初始化
 */
@Configuration
public class MinioConfiguration {
    @Autowired
    private MinionProperties minionProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minionProperties.getEndpoint())
                .credentials(minionProperties.getAccessKey(), minionProperties.getSecretKey())
                .build();

    }
}
