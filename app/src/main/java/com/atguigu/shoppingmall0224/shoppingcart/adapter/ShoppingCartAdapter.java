package com.atguigu.shoppingmall0224.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.home.bean.GoodsBean;
import com.atguigu.shoppingmall0224.shoppingcart.utils.CartStorage;
import com.atguigu.shoppingmall0224.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：杨光福 on 2017/6/14 14:39
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<GoodsBean> datas;
    private final CheckBox checkboxAll;
    private final TextView tvShopcartTotal;
    private final CheckBox cbAll;




    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.AddSubView)
        com.atguigu.shoppingmall0224.shoppingcart.view.AddSubView AddSubView;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            //设置item的点击事件的监听
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //状态取反
                    GoodsBean goodsBean = datas.get(getLayoutPosition());
                    goodsBean.setCheck(!goodsBean.isCheck());

                    //刷新适配器
                    notifyItemChanged(getLayoutPosition());


                    //重新显示总价格
                    showTotalPrice();
                    //校验是否全选
                    checkAll();

                }
            });
        }
    }

    public void checkAll_none(boolean isCheck) {
        if(datas != null && datas.size() >0){
            int number = 0;

            for(int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //只要有一个不选中就设置非全选
                goodsBean.setCheck(isCheck);
                notifyItemChanged(i);
            }
        }else{
            checkboxAll.setChecked(false);
        }

    }

    public void checkAll() {
        if(datas != null && datas.size() >0){
            int number = 0;

            for(int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //只要有一个不选中就设置非全选
                if(!goodsBean.isCheck()){
                    cbAll.setChecked(false);
                    checkboxAll.setChecked(false);
                }else{
                    number ++;
                }
            }

            if(number ==datas.size()){
                cbAll.setChecked(true);
                checkboxAll.setChecked(true);
            }


        }else {
            //没有数据
            cbAll.setChecked(false);
            checkboxAll.setChecked(false);
        }

    }

    public ShoppingCartAdapter(Context mContext, ArrayList<GoodsBean> datas, CheckBox checkboxAll, TextView tvShopcartTotal, CheckBox cbAll) {
        this.context = mContext;
        this.datas = datas;
        this.checkboxAll = checkboxAll;
        this.tvShopcartTotal = tvShopcartTotal;
        this.cbAll = cbAll;
        showTotalPrice();
        checkAll();
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计:"+getTotalPrice());

    }

    private double getTotalPrice() {
        double result = 0;
        if(datas != null && datas.size() > 0){
            for(int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //判断是否勾选
                if(goodsBean.isCheck()){
                    result = result + goodsBean.getNumber()* Double.parseDouble(goodsBean.getCover_price());
                }
            }
        }
        return result;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_shop_cart, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//1.更加位置得到数据
        GoodsBean goodsBean = datas.get(position);

        //2.绑定数据
        holder.cbGov.setChecked(goodsBean.isCheck());
        //图片
        Glide.with(context).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.ivGov);
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥" + goodsBean.getCover_price());

        holder.AddSubView.setValue(goodsBean.getNumber());
        holder.AddSubView.setMinValue(1);
        //库存
        holder.AddSubView.setMaxValue(20);

    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    public void deleteData() {

        if(datas != null && datas.size() > 0){

            for(int i = 0; i < datas.size(); i++) {

                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isCheck()){
                    datas.remove(goodsBean);
                    //同步到本地
                    CartStorage.getInstance(context).deleteData(goodsBean);
                    notifyItemRemoved(i);
                    i--;
                }
            }



        }
    }
}
