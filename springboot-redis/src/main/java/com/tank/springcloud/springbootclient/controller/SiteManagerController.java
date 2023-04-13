package com.tank.springcloud.springbootclient.controller;

import com.alibaba.fastjson.JSON;
import com.tank.springcloud.springbootclient.commond.SiteVisitDTO;
import com.tank.springcloud.springbootclient.commond.VisitReqDTO;
import com.tank.springcloud.springbootclient.serivce.SiteManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点统计：
 * 1、统计站点总访问次数
 * 2、统计站点的总访问人数，当前访问者在访问人数中的排名（当前ip是所有访问ip中第多少位访问这个站点）
 * 3、每个子页面都有访问次数，访问总人数，当前ip的访问排名统计
 * 4、同一个ip,同一天内访问同一个子页面，pv次数只增加1次，隔天之后，再次访问pv+1
 * <p>
 * pv:page visite :每个页面的访问次数（一个合法ip,一天之内pv的统计次数只能+1）
 * hot：对同一个uri而言，用户触发一次，hot+1
 * uv:unique vistor：统计URI的访问ip数
 */
@RestController
public class SiteManagerController {

    protected static final Logger logger = LoggerFactory.getLogger(SiteManagerController.class);

    @Autowired
    private SiteManagerService siteManagerService;

    @RequestMapping("/visit")
    public SiteVisitDTO visit(VisitReqDTO reqDTO) {
        SiteVisitDTO dto = siteManagerService.visit(reqDTO);
        System.out.println(JSON.toJSONString(dto));
        return dto;
    }

}
