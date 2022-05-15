package com.tank.springcloud.springbootclient.strategy;

import com.tank.springcloud.springbootclient.model.SmsPlaceHolderParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;

/**
 * 匹配任意一个字符串，内容信息即可发送
 */
@Slf4j
public class SmsSendAnyMatchStrategy implements SmsSendRejectStrategy {
    @Override
    public void reject(SmsTemplateContext templateContext, SmsPlaceHolderParameter parameter) {
        Set<String> parameterSet = getParameterSet(parameter);
        if (CollectionUtils.intersection(templateContext.getPlaceHolderKeySet(), parameterSet).size() <= 0) {
            log.error("短信占位符替换参数与短信模板完全不匹配,templateContent = {},parameter = {}", templateContext.getTemplateContent(), parameter);
            throw new RuntimeException("短信占位符替换参数与短信模板完全不匹配");
        }
    }
}
