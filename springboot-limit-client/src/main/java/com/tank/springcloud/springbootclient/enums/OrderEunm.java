package com.tank.springcloud.springbootclient.enums;

public enum OrderEunm {
    SIGN("1","签约"),
    UNSIGN("2","解约"),
    CONTINUMSIGN("3","续签"),
    ;

    private String code ;
    private String desc ;

    OrderEunm(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
