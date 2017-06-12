package com.atguigu.shoppingmall0224.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.home.bean.HomeBean;
import com.atguigu.shoppingmall0224.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：杨光福 on 2017/6/12 15:29
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class ChannelAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context mContext, List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.datas = channel_info;

    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        HomeBean.ResultBean.ChannelInfoBean infoBean = datas.get(position);
        viewHolder.tvChannel.setText(infoBean.getChannel_name());

        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+infoBean.getImage())
                .into(viewHolder.ivChannel);


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_channel)
        ImageView ivChannel;
        @BindView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
