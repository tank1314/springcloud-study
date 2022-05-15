package com.tank.springcloud.springbootclient.strategy;

import com.tank.springcloud.springbootclient.model.SmsPlaceHolderParameter;
import com.tank.springcloud.springbootclient.util.JacksonUtils;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 短信发送拒绝策略
 */
public interface SmsSendRejectStrategy {
    /**
     * 判断是否拒绝发送短信
     *
     * @param templateContext 短信模板上下文
     * @param parameter       填充占位符的参数
     */
    void reject(SmsTemplateContext templateContext, SmsPlaceHolderParameter parameter);

    /**
     * 获取短信发送占位符替换参数Set(不包含value为null)
     *
     * @param parameter 填充占位符的参数
     * @return Set
     */
    @NonNull
    default Set<String> getParameterSet(SmsPlaceHolderParameter parameter) {
        Map<String, Object> parameterMap = getParameterMap(parameter);
        return parameterMap.keySet();
    }

    /**
     * 获取短信发送占位符替换参数Map(不包含value为null)
     *
     * @param parameter 填充占位符的参数
     * @return Map
     */
    @NonNull
    default Map<String, Object> getParameterMap(SmsPlaceHolderParameter parameter) {
        Map<String, Object> parameterMap = JacksonUtils.toMap(parameter);
        Map<String, Object> filteredParameterMap = new HashMap<>(4);
        if (parameterMap != null) {
            Set<Map.Entry<String, Object>> entrySet = parameterMap.entrySet();
            entrySet.forEach(stringObjectEntry -> {
                if (stringObjectEntry.getValue() != null) {
                    filteredParameterMap.put(stringObjectEntry.getKey(), stringObjectEntry.getValue());
                }
            });
        }
        return filteredParameterMap;
    }
}
