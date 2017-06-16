package com.atguigu.shoppingmall0224.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.atguigu.shoppingmall0224.community.fragment.CommunityFragment;
import com.atguigu.shoppingmall0224.home.fragment.HomeFragment;
import com.atguigu.shoppingmall0224.shoppingcart.fragment.ShoppingCartFragment;
import com.atguigu.shoppingmall0224.type.fragment.TypeFragment;
import com.atguigu.shoppingmall0224.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rg_main)
    RadioGroup rgMain;


    /**
     * Fragment的集合
     */
    private ArrayList<BaseFragment> fragments;
    /**
     * 选择某个Fragment的位置
     */
    private int position = 0;
    /**
     * 之前显示过的Fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        //初始多个页面对应的Fragment并且设置默认的Fragment页面
        initFragment();

        //设置RadioGroup的选中监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //设置默认选择首页
        rgMain.check(R.id.rb_home);

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_type:
                    position = 1;
                    break;
                case R.id.rb_community:
                    position = 2;
                    break;
                case R.id.rb_cart:
                    position = 3;
                    break;
                case R.id.rb_user:
                    position = 4;
                    break;

            }
            Fragment currentFragment = fragments.get(position);
            switchFragment(currentFragment);
        }
    }

    /**
     * 要显示的Fragment
     * @param currentFragment
     */
    private void switchFragment(Fragment currentFragment) {
        if(currentFragment != tempFragment){//不是同一个
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if(!currentFragment.isAdded()){

                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.frameLayout,currentFragment);

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
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
//        //一进入要显示的Fragment
//        switchFragment(fragments.get(position));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int checkid = intent.getIntExtra("checkid", R.id.rb_home);
        if(checkid ==R.id.rb_home){
            //选中首页
            rgMain.check(R.id.rb_home);
        }else if(checkid ==R.id.rb_cart){
            //选中购物车
            rgMain.check(R.id.rb_cart);
        }
    }

    public RadioGroup getRgMain(){
        return rgMain;
    }
}
