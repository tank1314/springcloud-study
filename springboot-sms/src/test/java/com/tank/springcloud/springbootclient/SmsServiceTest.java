package com.tank.springcloud.springbootclient;

import com.tank.springcloud.springbootclient.model.SmsPlaceHolderParameter;
import com.tank.springcloud.springbootclient.model.SmsSendParam;
import com.tank.springcloud.springbootclient.serivce.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootLimitClientApplication.class)
public class SmsServiceTest {
    @Resource
    SmsService smsService;

    @Test
    public void sendSms() {
        System.out.println("11111-init");
        SmsSendParam smsSendParam = new SmsSendParam();
        smsSendParam.setTemplateCode(1);
        SmsPlaceHolderParameter placeHolderParameter = new SmsPlaceHolderParameter();
        placeHolderParameter.setAddress("TestCity");
        placeHolderParameter.setTitle("TestTitle");
        placeHolderParameter.setStartTime("模拟当前时间");
        smsSendParam.setParameter(placeHolderParameter);
        smsService.send(smsSendParam);
    }
}
