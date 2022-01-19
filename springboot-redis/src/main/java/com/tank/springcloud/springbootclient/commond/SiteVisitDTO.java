package com.tank.springcloud.springbootclient.commond;

import java.io.Serializable;

public class SiteVisitDTO implements Serializable {
    private VisitVO siteVO;
    private VisitVO uriVO;

    public SiteVisitDTO(VisitVO siteVO, VisitVO uriVO) {
        this.siteVO = siteVO;
        this.uriVO = uriVO;
    }

    public SiteVisitDTO() {
    }

    public VisitVO getSiteVO() {
        return siteVO;
    }

    public void setSiteVO(VisitVO siteVO) {
        this.siteVO = siteVO;
    }

    public VisitVO getUriVO() {
        return uriVO;
    }

    public void setUriVO(VisitVO uriVO) {
        this.uriVO = uriVO;
    }
}
