package com.example.administrator.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.weight.AppActivityUtils;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 文件名：FundDetailsAdapter
 */
public class DaoDetailsAdapter extends CommonAdapter<String> {

    public DaoDetailsAdapter(Context context, ArrayList<String> datas) {
        super(context, R.layout.item_message, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String messageListBean, int position) {
        holder.setText(R.id.tv_date, messageListBean.toString())
        .setOnClickListener(R.id.ll_check, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppActivityUtils.StartJxxqActivity((Activity) mContext, "测试啊" );
            }
        })
        ;

    }
}
