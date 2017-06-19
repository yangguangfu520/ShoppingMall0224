package com.atguigu.shoppingmall0224.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.home.bean.TypeListBean;
import com.atguigu.shoppingmall0224.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：杨光福 on 2017/6/19 11:18
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {


    private final Context mContext;
    private final List<TypeListBean.ResultBean.PageDataBean> datas;


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public GoodsListAdapter(Context mContext, List<TypeListBean.ResultBean.PageDataBean> page_data) {
        this.mContext = mContext;
        this.datas = page_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_goods_list, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //根据位置得到对应的数据
        TypeListBean.ResultBean.PageDataBean pageDataBean = datas.get(position);

        //绑定数据
        holder.tvName.setText(pageDataBean.getName());
        holder.tvPrice.setText("￥"+pageDataBean.getCover_price());

        //设置图片
        String imageUrl = Constants.BASE_URL_IMAGE+pageDataBean.getFigure();
        //加载图片
        Glide.with(mContext).load(imageUrl).placeholder(R.drawable.new_img_loading_2).error(R.drawable.new_img_loading_2).into(holder.ivHot);


    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(datas.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    private OnItemClickListener onItemClickListener;
    /**
     * item的点击的监听器
     */
    public interface OnItemClickListener{
        public void onItemClick(TypeListBean.ResultBean.PageDataBean dataBean);
    }

    /**
     * 设置item的监听
     * @param l
     */
    public void setOnItemClickListener( OnItemClickListener l){
        this.onItemClickListener = l;
    }
}
