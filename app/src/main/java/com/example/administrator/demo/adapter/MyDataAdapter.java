package com.example.administrator.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.my.MyDataActivity;
import com.example.administrator.demo.entity.MyDataBean;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.List;

public class MyDataAdapter extends BaseQuickAdapter<MyDataBean, BaseViewHolder> {

    private boolean isShow;
    private List<MyDataBean> datas;

    public MyDataAdapter( @Nullable List<MyDataBean> data) {
        super(R.layout.item_mydata, data);
        datas = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyDataBean item) {
        ImageLoader.getInstance().loadingImage(mContext, item.getSrc(), helper.getView(R.id.iv),
                new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.color.blue);
        helper.setText(R.id.tv, item.getName())
                .setImageResource(R.id.select, item.isSelect() ? R.mipmap.yxz : R.mipmap.wxz)
                .addOnClickListener(R.id.select);
        if("-1".equals(item.getId())){
            helper.setGone(R.id.select, false);
        }else{
            helper.setGone(R.id.select, isShow);
        }

    }

    public void setShow(boolean isShow){
        this.isShow = isShow;
        notifyDataSetChanged();
    }

    public boolean getShow(){
        return isShow;
    }
}
