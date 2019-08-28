package com.example.administrator.demo.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.TrackBean;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.List;

public class TrackAdapter extends BaseQuickAdapter<TrackBean.FootprintBean, BaseViewHolder> {
    private List<TrackBean.FootprintBean> data;
    public TrackAdapter(@Nullable List<TrackBean.FootprintBean> data) {
        super(R.layout.item_zuji, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TrackBean.FootprintBean item) {
        if(helper.getPosition() % 2 == 0){
            if(item.isAdd()){
                ImageLoader.getInstance().loadingImage(mContext, R.mipmap.icon_zj_add, helper.getView(R.id.iv),null, R.drawable.defaulthead);
            }else{
                //if(TextUtils.isEmpty(item.getFootprintImgSrc())){
                //    helper.setGone(R.id.iv, false);
                //}else{
                    ImageLoader.getInstance().loadingImage(mContext, item.getFootprintImgSrc(), helper.getView(R.id.iv),
                            new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.defaulthead);
                //}
            }

            helper.setText(R.id.tv, item.getFootprintName())
                    .setGone(R.id.iv1, false)
                    .setGone(R.id.tv1, false)
                    .setGone(R.id.lin1, false)
                    .setGone(R.id.iv, true)
                    .setGone(R.id.tv, true)
                    .setGone(R.id.lin, !(data.size()-1 == helper.getPosition()));
        }else{

            if(item.isAdd()){
                ImageLoader.getInstance().loadingImage(mContext, R.mipmap.icon_zj_add, helper.getView(R.id.iv1),null, R.drawable.defaulthead);
            }else{
                //if(TextUtils.isEmpty(item.getFootprintImgSrc())){
                //    helper.setGone(R.id.iv1, false);
                //}else{
                    ImageLoader.getInstance().loadingImage(mContext, item.getFootprintImgSrc(), helper.getView(R.id.iv1),
                            new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.defaulthead);
                //}
            }

            helper.setText(R.id.tv1, item.getFootprintName())
                    .setGone(R.id.iv1, true)
                    .setGone(R.id.tv1, true)
                    .setGone(R.id.lin1, !(data.size()-1 == helper.getPosition()))
                    .setGone(R.id.iv, false)
                    .setGone(R.id.tv, false)
                    .setGone(R.id.lin, false);
        }
    }
}
