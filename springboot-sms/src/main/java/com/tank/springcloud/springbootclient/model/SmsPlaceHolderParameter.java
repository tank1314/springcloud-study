package com.tank.springcloud.springbootclient.model;

import lombok.Data;

/**
 * 短信占位符参数
 */
@Data
public class SmsPlaceHolderParameter {
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 地址
     */
    private String address;

    /**
     * 链接
     */
    private String url;
}
