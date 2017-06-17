package com.atguigu.shoppingmall0224.community.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.atguigu.shoppingmall0224.community.adapter.CommunityViewPagerAdapter;
import com.atguigu.shoppingmall0224.home.activity.MainActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：杨光福 on 2017/6/12 10:29
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class CommunityFragment extends BaseFragment {
    private static final String TAG = CommunityFragment.class.getSimpleName();//"CommunityFragment"
    @BindView(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @BindView(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    Unbinder unbinder1;
    private ArrayList<BaseFragment> fragments;
    private CommunityViewPagerAdapter pagerAdapter;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG, "初始化社区控件...");
        View rootView = View.inflate(mContext, R.layout.fragment_community, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "绑定数据到控件上...");
        fragments = new ArrayList<>();
        fragments.add(new NewPostFragment());
        fragments.add(new HotPostFragment());

        MainActivity mainActivity = (MainActivity) mContext;
        //设置适配器
        pagerAdapter = new CommunityViewPagerAdapter(mainActivity.getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);

        tablayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ib_community_icon, R.id.ib_community_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_community_icon:
                Toast.makeText(mContext, "图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_community_message:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
