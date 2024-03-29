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
 * 文件名：FundDetailsAdapter
 */
public class MyReadAdapter extends CommonAdapter<ReadBean.BookInfosBean> {

    public MyReadAdapter(Context context, ArrayList<ReadBean.BookInfosBean> datas) {
        super(context, R.layout.item_red, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ReadBean.BookInfosBean messageListBean, int position) {
        ImageLoader.getInstance().loadingImage(mContext, ApiKeys.getApiUrl() + Address.fileId + messageListBean.getBeforeCoverUrl(), holder.getView(R.id.iv),
                new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.deful_back);
    }
}
