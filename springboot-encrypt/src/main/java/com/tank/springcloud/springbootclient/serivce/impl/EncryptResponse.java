package com.tank.springcloud.springbootclient.serivce.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tank.springcloud.springbootclient.annotation.Encrypt;
import com.tank.springcloud.springbootclient.model.base.RespBean;
import com.tank.springcloud.springbootclient.properties.EncryptProperties;
import com.tank.springcloud.springbootclient.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<RespBean> {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private EncryptProperties encryptProperties;

    /**
     * 判断什么样的接口需要加密， 方法中包含：@Encrypt注解，则对接口进行加密处理
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    /**
     * 数据返回前对数据加工处理
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean beforeBodyWrite(RespBean body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        byte[] bytes = encryptProperties.getKey().getBytes();
        try {
            if (body.getMsg() != null) {
                body.setMsg(AESUtil.encrypt(body.getMsg().getBytes(), bytes));
            }
            if (body.getObj() != null) {
                body.setObj(AESUtil.encrypt(mapper.writeValueAsBytes(body.getObj()), bytes));
            }

        } catch (Exception e) {
            System.out.println("加密异常" + e.getMessage());
            throw new RuntimeException();
        }
        return body;
    }
}
