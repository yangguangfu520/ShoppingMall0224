package com.atguigu.shoppingmall0224.type.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.base.BaseFragment;

/**
 * 作者：杨光福 on 2017/6/16 15:40
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class ListFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("分类Fragment的内容");
    }
}
