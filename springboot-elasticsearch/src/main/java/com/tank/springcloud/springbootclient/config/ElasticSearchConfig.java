package com.tank.springcloud.springbootclient.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 客户端初始化
 */
@Configuration
public class ElasticSearchConfig {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

}
