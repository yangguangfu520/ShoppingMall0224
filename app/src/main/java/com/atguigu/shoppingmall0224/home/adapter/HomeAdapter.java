package com.atguigu.shoppingmall0224.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.home.bean.HomeBean;
import com.atguigu.shoppingmall0224.home.utils.GlideImageLoader;
import com.atguigu.shoppingmall0224.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

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
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        }
      else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_item, null));
        }
         /*
        else if (viewType == ACT) {
            return new ActViewHolder(mContext, inflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, inflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, inflater.inflate(R.layout.hot_item, null));
        }*/

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(result.getBanner_info());
        }
       else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //绑定数据
            channelViewHolder.setData(result.getChannel_info());
        }

        /* else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }*/
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
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
            for(int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE+banner_info.get(i).getImage());
            }

            banner.setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                            Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private GridView gv;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv = (GridView) itemView.findViewById(R.id.gv);
        }

        public void setData(final List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            //设置适配器
            ChannelAdapter adapter = new ChannelAdapter(mContext,channel_info);
            gv.setAdapter(adapter);

            //设置点击某一条的监听
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HomeBean.ResultBean.ChannelInfoBean channelInfoBean = channel_info.get(position);
                    Toast.makeText(mContext, ""+channelInfoBean.getChannel_name(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }



}
