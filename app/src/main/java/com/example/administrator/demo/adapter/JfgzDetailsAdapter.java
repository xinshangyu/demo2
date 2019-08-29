package com.example.administrator.demo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.JFGZBean;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 文件名：积分规则
 */
public class JfgzDetailsAdapter extends CommonAdapter<JFGZBean.ProtocolRulesBean> {

    public JfgzDetailsAdapter(Context context, ArrayList<JFGZBean.ProtocolRulesBean> datas) {
        super(context, R.layout.item_jfgz, datas);
    }

    @Override
    protected void convert(ViewHolder holder, JFGZBean.ProtocolRulesBean messageListBean, int position) {
        TextView tv_content = holder.getView(R.id.tv_content);
        holder.setText(R.id.tv_name, messageListBean.getProtocolName())
                .setText(R.id.tv_content, messageListBean.getProtocolCount())
                .setOnClickListener(R.id.iv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_content.setVisibility(tv_content.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                    }
                })
        ;

    }
}
