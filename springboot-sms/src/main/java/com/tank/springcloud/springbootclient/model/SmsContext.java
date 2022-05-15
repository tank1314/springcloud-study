package com.tank.springcloud.springbootclient.model;

import lombok.Data;

/**
 * 短信上下文
 */
@Data
public class SmsContext {
    private String mobile;
    private String content;
}
