package com.atguigu.shoppingmall0224.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.atguigu.shoppingmall0224.home.activity.MainActivity;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

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

public class TypeFragment extends BaseFragment {
    private static final String TAG = TypeFragment.class.getSimpleName();//"TypeFragment"
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    Unbinder unbinder;
    private java.lang.String[] titles = {"分类","标签"};
    //装Fragment的集合
    private ArrayList<BaseFragment> fragments;
    private Fragment tempFragment;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG, "初始化分类控件...");
        View rootView = View.inflate(mContext, R.layout.fragment_type, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "绑定数据到控件上...");



        //设置内容
        tl1.setTabData(titles);
        //监听Tab的状态
        tl1.setOnTabSelectListener(new MyOnTabSelectListener());
        //初始化并且显示
        initFragment();



    }

    /**
     * 要显示的Fragment
     * @param currentFragment
     */
    private void switchFragment(Fragment currentFragment) {
        if(currentFragment != tempFragment){//不是同一个
            FragmentTransaction ft = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();

            if(!currentFragment.isAdded()){

                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.fl_type,currentFragment);

            }else{
                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把当前的显示
                ft.show(currentFragment);
            }


            //提交
            ft.commit();

            tempFragment = currentFragment;

        }

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());

        //设置显示默认的Fragment
        switchFragment(fragments.get(0));
    }

    class MyOnTabSelectListener implements OnTabSelectListener{

        @Override
        public void onTabSelect(int position) {
            Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
            switchFragment(fragments.get(position));
        }

        @Override
        public void onTabReselect(int position) {

        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_type_search)
    public void onViewClicked() {
    }
}
