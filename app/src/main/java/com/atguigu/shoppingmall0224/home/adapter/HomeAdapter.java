package com.atguigu.shoppingmall0224.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.app.GoodsInfoActivity;
import com.atguigu.shoppingmall0224.home.bean.HomeBean;
import com.atguigu.shoppingmall0224.home.utils.GlideImageLoader;
import com.atguigu.shoppingmall0224.home.view.MyGridView;
import com.atguigu.shoppingmall0224.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

/**
 * 作者：杨光福 on 2017/6/12 14:07
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class HomeAdapter extends RecyclerView.Adapter {
    /*
    上下文
     */
    private final Context mContext;
    private final HomeBean.ResultBean result;

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    /**
     * 当前类型
     */
    public int currentType = BANNER;



    private LayoutInflater inflater;

    public HomeAdapter(Context mContext, HomeBean.ResultBean result) {
        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);

    }

    /**
     * 根据位置得到当前是什么类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        //全部写完的时候修改成6，只实现一个类型的话就返回1
        return 6;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, inflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, inflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, inflater.inflate(R.layout.hot_item, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //绑定数据
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(result.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner 数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }

            banner.setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                            Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private GridView gv;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv = (GridView) itemView.findViewById(R.id.gv);
        }

        public void setData(final List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            //设置适配器
            ChannelAdapter adapter = new ChannelAdapter(mContext, channel_info);
            gv.setAdapter(adapter);

            //设置点击某一条的监听
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HomeBean.ResultBean.ChannelInfoBean channelInfoBean = channel_info.get(position);
                    Toast.makeText(mContext, "" + channelInfoBean.getChannel_name(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> act_info) {
            //设置ViewPager的适配器
            ViewPagerAdapter adapter = new ViewPagerAdapter(mContext, act_info);
            act_viewpager.setAdapter(adapter);
            //设置间距
            act_viewpager.setPageMargin(20);
            act_viewpager.setPageTransformer(true, new
                    RotateYTransformer());

            //设置点击事件
            adapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    HomeBean.ResultBean.ActInfoBean actInfoBean = act_info.get(position);
                    Toast.makeText(mContext, "" + actInfoBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    private boolean isFrist = false;

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.countdownview)
        CountdownView countdownview;
        @BindView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @BindView(R.id.rv_seckill)
        RecyclerView rvSeckill;
        Handler mHandler = new Handler();
        private HomeBean.ResultBean.SeckillInfoBean seckillInfo;


        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            this.seckillInfo = seckill_info;
            //1.设置适配器
            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, seckill_info.getList());
            rvSeckill.setAdapter(adapter);
            //2.设置布局管理器
            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            //设置item的点击事件的监听
            adapter.setOnItemClickListener(new SeckillRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "" + seckill_info.getList().get(position).getCover_price(), Toast.LENGTH_SHORT).show();
                }
            });


            if (!isFrist) {
                isFrist = true;
                //计算倒计时持续的时间
                long totalTime = Long.parseLong(seckillInfo.getEnd_time()) - Long.parseLong(seckillInfo.getStart_time());

                // 校对倒计时
                long curTime = System.currentTimeMillis();
                //重新设置结束数据时间
                seckillInfo.setEnd_time((curTime + totalTime + ""));
                //开始刷新
                startRefreshTime();

            }


        }


        /**
         * 开始刷新
         */
        void startRefreshTime() {
            mHandler.postDelayed(mRefreshTimeRunnable, 10);
        }

        Runnable mRefreshTimeRunnable = new Runnable() {
            @Override
            public void run() {
                //得到当前时间
                long currentTime = System.currentTimeMillis();

                if (currentTime >= Long.parseLong(seckillInfo.getEnd_time())) {
                    // 倒计时结束
                    mHandler.removeCallbacksAndMessages(null);
                } else {
                    //更新时间
                    countdownview.updateShow(Long.parseLong(seckillInfo.getEnd_time()) - currentTime);
                    //每隔1000毫秒更新一次
                    mHandler.postDelayed(mRefreshTimeRunnable, 1000);
                }

            }
        };
    }


    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @BindView(R.id.gv_recommend)
        GridView gvRecommend;

        public RecommendViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);

        }

        public void setData(final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
            //1.设置适配器
            RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(mContext, recommend_info);
            gvRecommend.setAdapter(adapter);

            //设置item的点击事件
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "" + recommend_info.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @BindView(R.id.gv_hot)
        MyGridView gvHot;
        public HotViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this,itemView);
        }

        public void setData(final List<HomeBean.ResultBean.HotInfoBean> hot_info) {

            //设置适配器
            HotGridViewAdapter adapter = new HotGridViewAdapter(mContext,hot_info);
            gvHot.setAdapter(adapter);
            //设置item的点击事件
            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, ""+hot_info.get(position).getName(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    mContext.startActivity(intent);
                }
            });


        }
    }


}
