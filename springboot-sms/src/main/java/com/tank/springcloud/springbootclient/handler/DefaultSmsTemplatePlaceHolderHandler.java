package com.tank.springcloud.springbootclient.handler;

import com.tank.springcloud.springbootclient.constant.SmsConstant;
import com.tank.springcloud.springbootclient.model.SmsPlaceHolderParameter;
import com.tank.springcloud.springbootclient.strategy.SmsSendRejectStrategy;
import com.tank.springcloud.springbootclient.strategy.SmsTemplateContext;
import com.tank.springcloud.springbootclient.util.JacksonUtils;
import com.tank.springcloud.springbootclient.util.PlaceHolderUtils;

/**
 * 默认短信模板占位符处理器
 */
public class DefaultSmsTemplatePlaceHolderHandler implements SmsTemplatePlaceHolderHandler {
    private SmsSendRejectStrategy rejectStrategy;

    public DefaultSmsTemplatePlaceHolderHandler(SmsSendRejectStrategy rejectStrategy) {
        this.rejectStrategy = rejectStrategy;
    }

    @Override
    public String handle(SmsTemplateContext templateContext, SmsPlaceHolderParameter parameter) {
        rejectStrategy.reject(templateContext, parameter);
        return PlaceHolderUtils.replacePlaceHolder(templateContext.getTemplateContent(),
                JacksonUtils.toMap(parameter),
                SmsConstant.DEFAULT_PLACE_HOLDER_REGEX,
                SmsConstant.DEFAULT_PLACE_HOLDER_KEY_REGEX);
    }
}
