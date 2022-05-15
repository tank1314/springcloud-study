package com.tank.springcloud.springbootclient.handler;

import com.tank.springcloud.springbootclient.model.SmsPlaceHolderParameter;
import com.tank.springcloud.springbootclient.strategy.SmsTemplateContext;

/**
 * 短信占位符处理器
 */
public interface SmsTemplatePlaceHolderHandler {
    /**
     *
     * @param templateContext 短信模板上下文
     * @param parameter 替换占位符的参数
     * @return
     */
    String handle(SmsTemplateContext templateContext, SmsPlaceHolderParameter parameter);
}
