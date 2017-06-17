package com.atguigu.shoppingmall0224.user.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.hankkin.gradationscroll.GradationScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.atguigu.shoppingmall0224.R.id.rl_header;

/**
 * 作者：杨光福 on 2017/6/12 10:29
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class UserFragment extends BaseFragment {
    private static final String TAG = UserFragment.class.getSimpleName();//"UserFragment"
    @BindView(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.tv_user_pay)
    TextView tvUserPay;
    @BindView(R.id.tv_user_receive)
    TextView tvUserReceive;
    @BindView(R.id.tv_user_finish)
    TextView tvUserFinish;
    @BindView(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @BindView(R.id.tv_user_location)
    TextView tvUserLocation;
    @BindView(R.id.tv_user_collect)
    TextView tvUserCollect;
    @BindView(R.id.tv_user_coupon)
    TextView tvUserCoupon;
    @BindView(R.id.tv_user_score)
    TextView tvUserScore;
    @BindView(R.id.tv_user_prize)
    TextView tvUserPrize;
    @BindView(R.id.tv_user_ticket)
    TextView tvUserTicket;
    @BindView(R.id.tv_user_invitation)
    TextView tvUserInvitation;
    @BindView(R.id.tv_user_callcenter)
    TextView tvUserCallcenter;
    @BindView(R.id.tv_user_feedback)
    TextView tvUserFeedback;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.scrollview)
    GradationScrollView scrollview;
    @BindView(R.id.tv_usercenter)
    TextView tvUsercenter;
    @BindView(R.id.ib_user_setting)
    ImageButton ibUserSetting;
    @BindView(R.id.ib_user_message)
    ImageButton ibUserMessage;
    Unbinder unbinder;
    int height = 0;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG, "初始化用户控件...");
        View rootView = View.inflate(mContext, R.layout.fragment_user, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "绑定数据到控件上...");

        initListeners();
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {
        tvUsercenter.setBackgroundColor(Color.argb((int) 0, 255,0,0));
        ViewTreeObserver vto = rlHeader.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                tvUsercenter.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                //图片的高
                height = rlHeader.getHeight();

                scrollview.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                            // TODO Auto-generated method stub
                            if (y <= 0) {   //设置标题的背景颜色
                                tvUsercenter.setBackgroundColor(Color.argb((int) 0, 255,0,0));
                            } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                                float scale = (float) y / height;
                                float alpha = (255 * scale);
                                //滑动距离 ： 总距离 = 改变的透明度 ： 总透明度
                                //改变的透明度 = (滑动距离 ：总距离) *总透明度

                                tvUsercenter.setTextColor(Color.argb((int) alpha, 255,255,255));
                                tvUsercenter.setBackgroundColor(Color.argb((int) alpha, 255,0,0));
                            } else {    //滑动到banner下面设置普通颜色
                                tvUsercenter.setBackgroundColor(Color.argb((int) 255, 255,0,0));
                            }
                    }
                });
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_username)
    public void onViewClicked() {
    }
}
