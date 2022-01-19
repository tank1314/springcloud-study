package com.tank.springcloud.springbootclient.serivce;

import org.springframework.stereotype.Service;

@Service
public class FallBackService implements ServerClient {
    @Override
    public String add(Integer a, Integer b) {
        return "server happen exception";
    }
}
