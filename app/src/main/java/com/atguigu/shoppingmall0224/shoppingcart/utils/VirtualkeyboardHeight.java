package com.atguigu.shoppingmall0224.shoppingcart.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * 作者：杨光福 on 2017/6/16 09:58
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class VirtualkeyboardHeight {

    // 获取屏幕和底部的高度
    public static int getDpi(Context context){

        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();

        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi=displayMetrics.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }

        return dpi;
    }

    // 获取底部虚拟键盘的高度
    public static  int getBottomStatusHeight(Context context){
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return totalHeight  - contentHeight;
    }

    // 获取屏幕的高度
    public static int getScreenHeight(Context context){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.heightPixels;
    }
}

