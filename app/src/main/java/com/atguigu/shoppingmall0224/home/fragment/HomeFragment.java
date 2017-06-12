package com.atguigu.shoppingmall0224.home.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.base.BaseFragment;

/**
 * 作者：杨光福 on 2017/6/12 10:29
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();//"HomeFragment"
    private TextView textView;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG,"初始化主页控件...");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"绑定数据到控件上...");
        textView.setText("我是主页内容");
    }
}
