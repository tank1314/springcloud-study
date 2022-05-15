package com.tank.springcloud.springbootclient.serivce;

import com.tank.springcloud.springbootclient.config.SmsConfig;
import com.tank.springcloud.springbootclient.constant.SmsInfo;
import com.tank.springcloud.springbootclient.enums.SmsSendRejectStrategyEnum;
import com.tank.springcloud.springbootclient.factory.SmsSendRejectStrategyFactory;
import com.tank.springcloud.springbootclient.handler.DefaultSmsTemplatePlaceHolderHandler;
import com.tank.springcloud.springbootclient.handler.SmsTemplatePlaceHolderHandler;
import com.tank.springcloud.springbootclient.model.SmsContext;
import com.tank.springcloud.springbootclient.model.SmsSendParam;
import com.tank.springcloud.springbootclient.strategy.SmsTemplateContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 短信业务处理类
 */
@Service
public class SmsService {

    @Resource
    private SmsConfig smsConfig;

    private SmsTemplatePlaceHolderHandler placeHolderHandler;

    @PostConstruct
    public void init() {
        SmsSendRejectStrategyEnum rejectStrategyEnum = smsConfig.getDefaultRejectStrategyEnum();
        if (rejectStrategyEnum == null) {
            rejectStrategyEnum = SmsSendRejectStrategyEnum.TOTALLY_MATCH;
        }
        placeHolderHandler = new DefaultSmsTemplatePlaceHolderHandler(SmsSendRejectStrategyFactory.getStrategy(rejectStrategyEnum));
    }

    public void send(SmsSendParam param) {
        String templateContent = smsConfig.getTemplates().get(param.getTemplateCode());
        if (templateContent == null) {
            throw new RuntimeException("不正确的短信模板");
        }
        SmsTemplateContext templateContext = SmsTemplateContext.from(templateContent, param.getTemplateCode());
        String sendContent = placeHolderHandler.handle(templateContext, param.getParameter());
        SmsContext smsContext = new SmsContext();
        smsContext.setContent(sendContent);
        smsContext.setMobile("100110100");
        SmsInfo.from(smsContext, templateContext);
        System.out.println(sendContent);
        //smsApi.sendSms(sendContent, "1341234124");
    }
}
