package com.tank.springcloud.springbootclient;
import com.tank.springcloud.springbootclient.util.PlaceHolderUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TestUtil {
    @Test
    public void pojo2Map() {
        String content ="尊敬的用户您好，{brand}定于{time}开展主题为{title}的营销活动，欢迎您的光临!";
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("brand","NIKE");
        resultMap.put("time", LocalDateTime.now()+"");
        resultMap.put("title","疫情过去，暖日一夏");
        String s = PlaceHolderUtils.replacePlaceHolder(content, resultMap, "","");
        System.out.println(s);
    }
}
