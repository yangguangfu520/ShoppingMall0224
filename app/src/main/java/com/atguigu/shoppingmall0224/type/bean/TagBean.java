package com.atguigu.shoppingmall0224.type.bean;

import java.util.List;

/**
 * 作者：杨光福 on 2017/6/17 11:27
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class TagBean {


    /**
     * code : 200
     * msg : 请求成功
     * result : [{"name":"尚硅谷","tag_id":"1"},{"name":"JAVA","tag_id":"2"},{"name":"Android","tag_id":"3"},{"name":"HTML5","tag_id":"4"},{"name":"PHP","tag_id":"5"},{"name":"UI","tag_id":"6"},{"name":"Activity","tag_id":"7"},{"name":"Fragment","tag_id":"8"},{"name":"Button","tag_id":"9"},{"name":"TextView","tag_id":"10"},{"name":"JNI","tag_id":"11"},{"name":"NDK","tag_id":"12"},{"name":"手机影音","tag_id":"13"},{"name":"硅谷社交","tag_id":"14"},{"name":"硅谷商城","tag_id":"15"},{"name":"硅谷金融","tag_id":"16"},{"name":"自定义控件","tag_id":"17"},{"name":"硅谷","tag_id":"18"},{"name":"OKHttp","tag_id":"19"},{"name":"Volley","tag_id":"20"},{"name":"xUtils","tag_id":"21"},{"name":"Imageloader","tag_id":"22"},{"name":"Glide","tag_id":"23"},{"name":"尚硅谷","tag_id":"24"},{"name":"WEB基础","tag_id":"25"},{"name":"混合开发","tag_id":"26"},{"name":"尚硅谷","tag_id":"27"},{"name":"棒棒达","tag_id":"31"},{"name":"么么哒","tag_id":"32"},{"name":"呵呵哒","tag_id":"33"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * name : 尚硅谷
         * tag_id : 1
         */

        private String name;
        private String tag_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }
    }
}
