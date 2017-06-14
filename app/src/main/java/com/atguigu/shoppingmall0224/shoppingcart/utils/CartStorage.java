package com.atguigu.shoppingmall0224.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shoppingmall0224.home.bean.GoodsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * 作者：杨光福 on 2017/6/14 09:23
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    public static CartStorage instance;
    private static Context mContext;
    /**
     * 数据存储在内存中
     */
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage() {
        //初始化集合
        sparseArray = new SparseArray<>();
        listTosparseArray();
    }

    private void listTosparseArray() {
        //得到所有数据
        ArrayList<GoodsBean> datas = getAllData();
        for (int i = 0; i < datas.size(); i++) {
            GoodsBean goodsBean = datas.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);

        }
    }

    /**
     * 得到所有数据
     *
     * @return
     */
    public ArrayList<GoodsBean> getAllData() {
        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    private ArrayList<GoodsBean> getLocalData() {
        ArrayList<GoodsBean> datas = new ArrayList<>();
        //json数据
        String saveJson = CacheUtils.getString(mContext, JSON_CART);
        if (!TextUtils.isEmpty(saveJson)) {
            //把保存的json数据解析成ArrayList数组
            datas = new Gson().fromJson(saveJson, new TypeToken<ArrayList<GoodsBean>>() {
            }.getType());
        }
        return datas;
    }


    /**
     * 得到CartStorage
     *
     * @return
     */
    public static CartStorage getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            //加上锁
            synchronized (CartStorage.class) {
                if (instance == null) {
                    instance = new CartStorage();
                }
            }
        }

        return instance;
    }

    /**
     * 添加数据
     *
     * @param bean
     */
    public void addData(GoodsBean bean) {
        //查看内容中是否存在
        GoodsBean temp = sparseArray.get(Integer.parseInt(bean.getProduct_id()));
        if (temp != null) {
            //存在，就修改
            temp.setNumber(bean.getNumber());
        } else {
            //如果不存在，保存到内存中
            temp = bean;
        }

        //内存中更新
        sparseArray.put(Integer.parseInt(temp.getProduct_id()), temp);


        //同步到本地
        commit();


    }


    /**
     * 添加数据
     *
     * @param bean
     */
    public void deleteData(GoodsBean bean) {
        //内存中更新
        sparseArray.delete(Integer.parseInt(bean.getProduct_id()));
        //同步到本地
        commit();

    }


    /**
     * 添加数据
     *
     * @param bean
     */
    public void updateData(GoodsBean bean) {
        //内存中更新
        sparseArray.put(Integer.parseInt(bean.getProduct_id()),bean);
        //同步到本地
        commit();

    }

    /**
     * 在本地保存一份
     */
    private void commit() {
        //把SparseArray 转换成List集合
        ArrayList<GoodsBean> goodsBeens = sparseArrayToList();
        //使用Gson把List集合转换成json的String数据
        String json = new Gson().toJson(goodsBeens);
        //把文本保存到sp中
        CacheUtils.putString(mContext,JSON_CART,json);


    }

    private ArrayList<GoodsBean> sparseArrayToList() {
        ArrayList<GoodsBean> goodsBeens = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeens.add(goodsBean);
        }
        return goodsBeens;
    }


}
