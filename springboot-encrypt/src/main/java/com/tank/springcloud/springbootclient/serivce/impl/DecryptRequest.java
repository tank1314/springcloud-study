package com.tank.springcloud.springbootclient.serivce.impl;

import com.tank.springcloud.springbootclient.annotation.Decrypt;
import com.tank.springcloud.springbootclient.properties.EncryptProperties;
import com.tank.springcloud.springbootclient.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class DecryptRequest extends RequestBodyAdviceAdapter {
    @Autowired
    EncryptProperties encryptProperties;

    /**
     * 判断哪些接口需要做解密处理
     * @param methodParameter
     * @param targetType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(Decrypt.class) || methodParameter.hasParameterAnnotation(Decrypt.class);
    }

    /**
     * 在参数转换为具体对象执行之前，从流中加载数据，然后对数据进行解密
     * @param inputMessage
     * @param parameter
     * @param targetType
     * @param converterType
     * @return
     * @throws IOException
     */
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
                                           Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {
        byte[] bytes = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(bytes);
        try {
            byte[] decrypt = AESUtil.decrypt(bytes, encryptProperties.getKey().getBytes());
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decrypt);
            return new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    return byteArrayInputStream;
                }

                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }
            };
        } catch (Exception e) {
            e.getMessage();
        }
        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }
}
