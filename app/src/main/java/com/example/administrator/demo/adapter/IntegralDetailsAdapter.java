package com.example.administrator.demo.adapter;

import android.content.Context;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.IntegralBean;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 文件名：积分记录
 */
public class IntegralDetailsAdapter extends CommonAdapter<IntegralBean.IntegralRecordBean> {

    public IntegralDetailsAdapter(Context context, ArrayList<IntegralBean.IntegralRecordBean> datas) {
        super(context, R.layout.item_message2, datas);
    }

    @Override
    protected void convert(ViewHolder holder, IntegralBean.IntegralRecordBean messageListBean, int position) {
        holder.setText(R.id.tv_date, messageListBean.getCommodityName())
                .setText(R.id.tv_text, "" + messageListBean.getIntegralNumber() + "积分")
                .setText(R.id.tv_money, "" + messageListBean.getCreateTime());

    }
}
