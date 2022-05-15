package com.tank.springcloud.springbootclient.constant;

/**
 * 短信内容常量
 */
public interface SmsConstant {
    String DEFAULT_PLACE_HOLDER_REGEX = "\\{[a-z].*?\\}";

    String DEFAULT_PLACE_HOLDER_KEY_REGEX = "[^(\\{)|(\\})]+";
}
