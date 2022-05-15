package com.tank.springcloud.springbootclient.strategy;

import com.tank.springcloud.springbootclient.model.SmsPlaceHolderParameter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * 完全匹配短信才发送
 */
@Slf4j
public class SmsSendTotallyMatchStrategy implements SmsSendRejectStrategy {
    @Override
    public void reject(SmsTemplateContext templateContext, SmsPlaceHolderParameter parameter) {
        Set<String> parameterSet = getParameterSet(parameter);
        if (!parameterSet.containsAll(templateContext.getPlaceHolderKeySet())) {
            log.error("短信占位符替换参数与短信模板不完全匹配,templateContent = {},parameter = {}", templateContext.getTemplateContent(), parameter);
            throw new RuntimeException("短信占位符替换参数与短信模板不完全匹配");
        }
    }
}
