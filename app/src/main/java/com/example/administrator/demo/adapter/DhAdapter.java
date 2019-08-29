package com.example.administrator.demo.adapter;

import android.content.Context;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.DhBean;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 兑换
 */
public class DhAdapter extends CommonAdapter<DhBean.DataBean.MallCommodityBean> {

    private Context context;

    public DhAdapter(Context context, ArrayList<DhBean.DataBean.MallCommodityBean> datas) {
        super(context, R.layout.item_dh, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder,DhBean.DataBean.MallCommodityBean messageListBean, int position) {
        holder.setText(R.id.tv_name, messageListBean.getCommodityName())
               ;

    }
}
