package com.example.administrator.demo.adapter;

import android.content.Context;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.ReadBean;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.ArrayList;

/**
 */
public class MyLisiAdapter extends CommonAdapter<String> {

    public MyLisiAdapter(Context context, ArrayList<String> datas) {
        super(context, R.layout.item_red, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String messageListBean, int position) {
//        holder.setText(R.id.tv_date, messageListBean.toString());

    }
}
