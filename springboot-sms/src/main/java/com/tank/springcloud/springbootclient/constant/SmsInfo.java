package com.tank.springcloud.springbootclient.constant;

import com.tank.springcloud.springbootclient.model.SmsContext;
import com.tank.springcloud.springbootclient.strategy.SmsTemplateContext;

public class SmsInfo {
    private static final ThreadLocal<SmsContext> SMS_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<SmsTemplateContext> SMS_TEMPLATE_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static void setSmsContext(SmsContext smsContext) {
        SMS_CONTEXT_THREAD_LOCAL.set(smsContext);
    }

    public static void setSmsTemplateContext(SmsTemplateContext smsTemplateContext) {
        SMS_TEMPLATE_CONTEXT_THREAD_LOCAL.set(smsTemplateContext);
    }

    public static SmsContext getSmsContext() {
        return SMS_CONTEXT_THREAD_LOCAL.get();
    }

    public static SmsTemplateContext getSmsTemplateContext() {
        return SMS_TEMPLATE_CONTEXT_THREAD_LOCAL.get();
    }

    public static void clear() {
        SMS_TEMPLATE_CONTEXT_THREAD_LOCAL.remove();
        SMS_CONTEXT_THREAD_LOCAL.remove();
    }

    public static void from(SmsContext smsContext, SmsTemplateContext smsTemplateContext) {
        setSmsContext(smsContext);
        setSmsTemplateContext(smsTemplateContext);
    }
}
