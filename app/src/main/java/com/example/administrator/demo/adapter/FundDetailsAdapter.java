package com.example.administrator.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.JyjlBean;
import com.example.administrator.demo.weight.AppActivityUtils;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 文件名：FundDetailsAdapter
 */
public class FundDetailsAdapter extends CommonAdapter<JyjlBean.VirtualCurrencyRecordBean> {

    public FundDetailsAdapter(Context context, ArrayList<JyjlBean.VirtualCurrencyRecordBean> datas) {
        super(context, R.layout.item_message, datas);
    }

    @Override
    protected void convert(ViewHolder holder, JyjlBean.VirtualCurrencyRecordBean messageListBean, int position) {
        holder.setText(R.id.tv_date, messageListBean.getDealWay())
                .setText(R.id.tv_text, messageListBean.getCreateTime())
                .setText(R.id.tv_money, "+"+messageListBean.getDealCost())

        .setOnClickListener(R.id.ll_check, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppActivityUtils.StartJxxqActivity((Activity) mContext, messageListBean.getDealCost(),messageListBean.getDealWay(),messageListBean.getCreateTime(),messageListBean.getDealId(),messageListBean.getSurplusCost() );
            }
        })
        ;

    }
}
