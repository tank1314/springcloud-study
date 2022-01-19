package com.tank.springcloud.springbootclient.commond.abstractCommond;

import com.tank.springcloud.springbootclient.interfaces.ShareContent;

import java.util.ResourceBundle;

public class WechatContent implements ShareContent {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public String showContent() {
        return resourceBundle.getString("share.wechat.content") ;
    }
}
