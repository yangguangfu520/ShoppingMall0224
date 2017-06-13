package com.atguigu.shoppingmall0224.home.bean;

import java.io.Serializable;

/**
 * 作者：杨光福 on 2017/6/13 15:29
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class WebViewBean implements Serializable {
    /**
     * icon_url : /operation/img/1478169868/1478761370286.png
     * name : 尚硅谷福利专区之111.1专区
     * url : /oper/1478169868app.html
     */

    private String icon_url;
    private String name;
    private String url;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
