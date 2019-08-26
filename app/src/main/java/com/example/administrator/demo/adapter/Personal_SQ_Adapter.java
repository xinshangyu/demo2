package com.example.administrator.demo.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;

/**
 * 商圈adapter
 */
public class Personal_SQ_Adapter extends CommonAdapter<String> {

    public Personal_SQ_Adapter(Context context, ArrayList<String> datas) {
        super(context, R.layout.item_personal_sq, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String messageListBean, int position) {
        holder.setText(R.id.tv_name, messageListBean.toString())
                .setOnClickListener(R.id.iv_dot, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//编辑 删除 隐藏的 布局有，用的时候告诉我，我没看懂    布局是dialog_delete_yc.xml

                    }
                });

    }
}
