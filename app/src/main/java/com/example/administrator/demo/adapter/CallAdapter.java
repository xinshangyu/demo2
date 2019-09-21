package com.example.administrator.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.Help2Bean;
import com.example.administrator.demo.weight.AppActivityUtils;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 猜你想问
 */
public class CallAdapter extends CommonAdapter<Help2Bean.FaqRecordBean> {

    private Context context;

    public CallAdapter(Context context, ArrayList<Help2Bean.FaqRecordBean> datas) {
        super(context, R.layout.item_call, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, Help2Bean.FaqRecordBean messageListBean, int position) {
        holder.setText(R.id.tv_name, "" + (position + 1) + "：" + messageListBean.getPreblemTitle())
                .setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AppActivityUtils.StartMoreActivity((Activity) mContext, "" + position);
                    }
                });

    }
}
