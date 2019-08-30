package com.example.administrator.demo.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.SQBean;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;
import com.shehuan.nicedialog.BaseNiceDialog;
import com.shehuan.nicedialog.NiceDialog;
import com.shehuan.nicedialog.ViewConvertListener;

import java.util.ArrayList;

/**
 * 商圈adapter
 */
public class Personal_SQ_Adapter extends CommonAdapter<SQBean.BizCircleBean> {
    private Context context;

    public Personal_SQ_Adapter(Context context, ArrayList<SQBean.BizCircleBean> datas) {
        super(context, R.layout.item_personal_sq, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, SQBean.BizCircleBean messageListBean, int position) {
        holder.setText(R.id.tv_name, messageListBean.getUserInfo().getNickName())
                .setText(R.id.tv_content, messageListBean.getContent())
                .setOnClickListener(R.id.iv_dot, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(view, holder, position);
                    }
                });
    }
}
