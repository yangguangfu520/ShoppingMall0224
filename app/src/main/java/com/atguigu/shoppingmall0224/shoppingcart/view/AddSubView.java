package com.atguigu.shoppingmall0224.shoppingcart.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 作者：杨光福 on 2017/6/14 10:55
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class AddSubView extends LinearLayout {
    private final Context mContext;
    @BindView(R.id.iv_sub)
    ImageView ivSub;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.iv_add)
    ImageView ivAdd;

    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        //注意要加上“”
        tvValue.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 在布局文件中实例化该类的时候，采用该构造方法实例化
     *
     * @param context
     * @param attrs
     */
    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //把布局文件实例化成View,并且添加到AddSubView内部
        ButterKnife.bind(this,View.inflate(context, R.layout.add_sub_view, this));

        if(attrs != null){
            //取出属性
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);

            int value = tintTypedArray.getInt(R.styleable.AddSubView_value, 0);//如果取不到就设置0
            if (value > 0) {//取到
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_minValue, 0);
            if (minValue > 0) {
                setMinValue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_maxValue, 0);
            if (maxValue > 0) {
                setMaxValue(maxValue);
            }
            Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberAddBackground);
            if (addDrawable != null) {
                ivAdd.setImageDrawable(addDrawable);
            }
            Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberSubBackground);
            if (subDrawable != null) {
                ivSub.setImageDrawable(subDrawable);
            }
        }




    }

    @OnClick({R.id.iv_sub, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sub:
//                Toast.makeText(mContext, "减少", Toast.LENGTH_SHORT).show();

                if(value > minValue){
                    value --;
                }
               tvValue.setText(value+"");


                break;
            case R.id.iv_add:
//                Toast.makeText(mContext, "增加", Toast.LENGTH_SHORT).show();
                if(value < maxValue){
                    value ++;
                }
                tvValue.setText(value+"");
                break;
        }

        if(changeListener != null){
            changeListener.onNumberChange(value);
        }

    }
    private OnNumberChangeListener changeListener;
    public interface OnNumberChangeListener{
        //当商品数量变化的时候回调
        public void onNumberChange(int number);
    }

    /**
     * 设置商品数量变化的监听
     * @param l
     */
    public void  setOnNumberChangeListener( OnNumberChangeListener l){
        this.changeListener = l;
    }

}
