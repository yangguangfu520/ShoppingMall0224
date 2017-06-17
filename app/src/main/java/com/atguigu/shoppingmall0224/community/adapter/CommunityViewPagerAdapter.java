package com.atguigu.shoppingmall0224.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.shoppingmall0224.base.BaseFragment;

import java.util.ArrayList;


/**
 * 作者：杨光福 on 2017/6/17 11:46
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class CommunityViewPagerAdapter extends FragmentPagerAdapter{
    private final ArrayList<BaseFragment> fragments;

    public CommunityViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    /**
     * 根据位置得到对应的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
