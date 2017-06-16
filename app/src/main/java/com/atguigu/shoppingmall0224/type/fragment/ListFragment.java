package com.atguigu.shoppingmall0224.type.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.atguigu.shoppingmall0224.type.adapter.TypeLeftAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：杨光福 on 2017/6/16 15:40
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class ListFragment extends BaseFragment {
    @BindView(R.id.lv_left)
    ListView lvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    Unbinder unbinder;
    private TypeLeftAdapter typeLeftAdapter;

    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    @Override
    public View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_list, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        //设置坐标ListView的适配器
        typeLeftAdapter = new TypeLeftAdapter(mContext,titles);
        lvLeft.setAdapter(typeLeftAdapter);

        //设置监听点击ListView的item的点击事件，并且点击的时候变效果
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1.记录点击的位置
                typeLeftAdapter.changeSelectPosition(position);
                //2.适配器刷新
                typeLeftAdapter.notifyDataSetChanged();//getView

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
