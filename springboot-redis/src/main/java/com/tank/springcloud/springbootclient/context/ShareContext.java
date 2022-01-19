package com.tank.springcloud.springbootclient.context;

import com.tank.springcloud.springbootclient.interfaces.ShareContent;
import com.tank.springcloud.springbootclient.interfaces.ShareImageUrl;
import com.tank.springcloud.springbootclient.interfaces.ShareLink;
import com.tank.springcloud.springbootclient.interfaces.ShareTitle;

import java.util.ResourceBundle;

public class ShareContext {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    protected ShareTitle shareTitle;
    protected ShareContent shareContent;
    protected ShareImageUrl shareImageUrl;
    protected ShareLink shareLink;

    public static ShareContent getShareTarget(Integer type) {
        String className = resourceBundle.getString(type.toString());
        Class cls;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final String showTitle() {
        return shareTitle.showTitle();
    }

    public final String showContent() {
        return shareContent.showContent();
    }

    public final String displayImageUrl(final String platform) {
        return shareImageUrl.showImageUrl(platform);
    }

    public final String displayShareLinkUrl(final String platform, final String  userToken) {
        return shareLink.getShareLink(platform, userToken);
    }

}
