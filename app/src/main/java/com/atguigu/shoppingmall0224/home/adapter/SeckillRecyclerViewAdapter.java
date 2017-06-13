package com.atguigu.shoppingmall0224.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.home.bean.HomeBean;
import com.atguigu.shoppingmall0224.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：杨光福 on 2017/6/13 09:34
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.MyViewHolder> {


    private final Context mContext;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public SeckillRecyclerViewAdapter(Context mContext, List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = mContext;
        this.datas = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1.根据位置得到对应的数据
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        //2.绑定数据
        holder.tvCoverPrice.setText("￥"+listBean.getCover_price());
        holder.tvOriginPrice.setText("￥"+listBean.getOrigin_price());

        //设置图片
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+listBean.getFigure())
                .into(holder.ivFigure);

    }



     class MyViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_figure)
        ImageView ivFigure;
        @BindView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //设置点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener != null){
                        //getLayoutPosition()当前点击View的对应在列表中的位置
                        itemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 监听器
     */
    public interface OnItemClickListener{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        public void onItemClick(int position);
    }

    private  OnItemClickListener itemClickListener;

    /**
     * 设置item的监听
     * @param l
     */
    public void  setOnItemClickListener(OnItemClickListener l){
        this.itemClickListener = l;
    }
}
