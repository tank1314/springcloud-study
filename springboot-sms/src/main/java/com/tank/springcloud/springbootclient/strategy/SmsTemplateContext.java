package com.tank.springcloud.springbootclient.strategy;

import com.tank.springcloud.springbootclient.constant.SmsConstant;
import com.tank.springcloud.springbootclient.util.PlaceHolderUtils;
import lombok.Data;

import java.util.Set;

/**
 * 短信模板上下文
 */
@Data
public class SmsTemplateContext {
    private Long templateId;
    private Integer templateCode;
    private Integer templateClassfiy;
    private String templateContent;
    /**
     * 占位符
     * 占位符: 前缀 + key + 后缀,例如 {url}
     */
    private Set<String> placeHolderKeySet;

    public static SmsTemplateContext from(String templateContent, Integer templateCode) {
        SmsTemplateContext context = new SmsTemplateContext();
        context.setTemplateContent(templateContent);
        context.setTemplateCode(templateCode);
        context.setPlaceHolderKeySet(PlaceHolderUtils.getPlaceHolderKeySet(templateContent, SmsConstant.DEFAULT_PLACE_HOLDER_REGEX, SmsConstant.DEFAULT_PLACE_HOLDER_KEY_REGEX));
        return context;
    }

    public int getPlaceHodlerKeyCount() {
        return this.placeHolderKeySet.size();
    }

}
