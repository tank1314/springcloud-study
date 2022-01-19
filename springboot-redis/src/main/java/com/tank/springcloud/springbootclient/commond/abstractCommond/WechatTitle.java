package com.tank.springcloud.springbootclient.commond.abstractCommond;

import com.tank.springcloud.springbootclient.interfaces.ShareContent;
import com.tank.springcloud.springbootclient.interfaces.ShareTitle;

import java.util.ResourceBundle;

public class WechatTitle implements ShareTitle {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Override
    public String showTitle() {
        return resourceBundle.getString("share.wechat.title") ;
    }

}

