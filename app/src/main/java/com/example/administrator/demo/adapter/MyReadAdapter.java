package com.example.administrator.demo.adapter;

import android.content.Context;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 文件名：FundDetailsAdapter
 */
public class MyReadAdapter extends CommonAdapter<String> {

    public MyReadAdapter(Context context, ArrayList<String> datas) {
        super(context, R.layout.item_red, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String messageListBean, int position) {
//        holder.setText(R.id.tv_date, messageListBean.toString());

    }
}
