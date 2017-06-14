package com.atguigu.shoppingmall0224.shoppingcart.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：尚硅谷-杨光福 on 2016/12/12 12:46
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：缓存工具类
 */
public class CacheUtils {
    /**
     * 得到String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }


    /**
     * 保持文本数据
     * @param context
     * @param key
     * @param value
     */
    public static void   putString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
