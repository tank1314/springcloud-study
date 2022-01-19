package com.tank.springcloud.springbootserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;
    @Value("${spring.application.name}")
    private String serverName;

    @RequestMapping(value = "/add")
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        List<ServiceInstance> instances = client.getInstances(serverName);
        Integer result = a + b;
        String localIp = null;
        logger.info("requestparams {}{}", a, b);
        for (ServiceInstance serviceInstance : instances) {
            String ip = serviceInstance.getHost();
            Integer port = serviceInstance.getPort();
            String serviceID = serviceInstance.getServiceId();
            if (getUrl().equals(ip)) {
                localIp = ip + ":" + port + "-" + serviceID;
                break;
            }
        }
        return localIp + "result= " + result;
    }

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }


}
