package com.tank.springcloud.springbootclient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = SpringbootRedisClientApplication.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class BaseSpringBootTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseSpringBootTest.class);

    @Before
    public void init() {
        logger.info("test begin...");
    }

    @After
    public void after() {
        logger.info("test end...");
    }

    @Test
    public void initTest() {
        System.out.println("success");
    }
}


