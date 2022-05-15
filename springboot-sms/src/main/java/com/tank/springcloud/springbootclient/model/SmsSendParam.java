package com.tank.springcloud.springbootclient.model;

import lombok.Data;

/**
 * 短信发送参数
 */
@Data
public class SmsSendParam {
    /**
     * 短信模板code
     */
    private Integer templateCode;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 占位符参数
     */
    private SmsPlaceHolderParameter parameter;
}
