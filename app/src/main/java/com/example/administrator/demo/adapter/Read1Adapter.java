package com.example.administrator.demo.adapter;

import android.content.Context;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

public class Read1Adapter extends CommonAdapter<String> {

    private Context context;

    public Read1Adapter(Context context, ArrayList<String> datas) {
        super(context, R.layout.item_read1, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, String messageListBean, int position) {
        holder.setText(R.id.tv_name, messageListBean.toString());

    }
}
