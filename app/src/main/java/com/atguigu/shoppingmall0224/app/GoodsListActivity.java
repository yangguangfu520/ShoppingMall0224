package com.atguigu.shoppingmall0224.app;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall0224.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsListActivity extends AppCompatActivity {

    @BindView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @BindView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @BindView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @BindView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @BindView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @BindView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @BindView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @BindView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @BindView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.dl_left)
    DrawerLayout dlLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                Toast.makeText(GoodsListActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home:
                Toast.makeText(GoodsListActivity.this, "主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_sort:
                Toast.makeText(GoodsListActivity.this, "综合排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_price:
                Toast.makeText(GoodsListActivity.this, "价格排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_select:
                Toast.makeText(GoodsListActivity.this, "筛选排序", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
