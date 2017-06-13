package com.atguigu.shoppingmall0224.home.bean;

import java.io.Serializable;

/**
 * 作者：杨光福 on 2017/6/13 14:31
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class GoodsBean implements Serializable{


    /**
     * cover_price : 138.00
     * figure : /supplier/1478873740576.jpg
     * name : 【尚硅谷】日常 萌系小天使卫衣--白色款
     * product_id : 10659
     */

    private String cover_price;
    private String figure;
    private String name;
    private String product_id;

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
