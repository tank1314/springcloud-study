package com.tank.springcloud.springbootclient.commond;

import java.io.Serializable;

public class VisitReqDTO implements Serializable {

    //应用
    private String app ;
    //访问者ip
    private String ip ;
    //访问者uri
    private String uri;

    public VisitReqDTO() {
    }

    public VisitReqDTO(String app, String ip, String uri) {
        this.app = app;
        this.ip = ip;
        this.uri = uri;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
