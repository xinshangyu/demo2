package com.example.administrator.demo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.TrackBean;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.utils.BaseUtils;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.List;

/**
 * 足迹
 */
public class TrackAdapter extends BaseQuickAdapter<TrackBean.FootprintBean, BaseViewHolder> {
    private List<TrackBean.FootprintBean> data;

    public TrackAdapter(@Nullable List<TrackBean.FootprintBean> data) {
        super(R.layout.item_zuji, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TrackBean.FootprintBean item) {
        if (helper.getPosition() % 2 == 0) {
            ImageView imageView = helper.getView(R.id.iv);
            if (item.isAdd()) {
                int size = BaseUtils.dip2px(mContext, 30);
                imageView.setPadding(size, size, size, size);
                ImageLoader.getInstance().loadingImage(mContext, R.mipmap.icon_zj_add, imageView, null, R.drawable.deful_back);
            } else {
                int size = BaseUtils.dip2px(mContext, 0);
                imageView.setPadding(size, size, size, size);
                ImageLoader.getInstance().loadingImage(mContext, ApiKeys.getApiUrl() + Address.fileId + item.getFootprintImgSrc(), imageView,
                        new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.deful_back);
            }

            helper.setText(R.id.tv, item.getFootprintName())
                    .setGone(R.id.iv1, false)
                    .setGone(R.id.tv1, false)
                    .setGone(R.id.lin1, false)
                    .setGone(R.id.iv, true)
                    .addOnClickListener(R.id.iv)
                    .setGone(R.id.tv, true)
                    .setGone(R.id.lin, !(data.size() - 1 == helper.getPosition()));
        } else {
            ImageView imageView = helper.getView(R.id.iv1);
            if (item.isAdd()) {
                int size = BaseUtils.dip2px(mContext, 30);
                imageView.setPadding(size, size, size, size);
                ImageLoader.getInstance().loadingImage(mContext, R.mipmap.icon_zj_add, imageView, null, R.drawable.deful_back);
            } else {
                int size = BaseUtils.dip2px(mContext, 0);
                imageView.setPadding(size, size, size, size);
                ImageLoader.getInstance().loadingImage(mContext, ApiKeys.getApiUrl() + Address.fileId + item.getFootprintImgSrc(), imageView,
                        new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.deful_back);
            }

            helper.setText(R.id.tv1, item.getFootprintName())
                    .setGone(R.id.iv1, true)
                    .setGone(R.id.tv1, true)
                    .addOnClickListener(R.id.iv1)
                    .setGone(R.id.lin1, !(data.size() - 1 == helper.getPosition()))
                    .setGone(R.id.iv, false)
                    .setGone(R.id.tv, false)
                    .setGone(R.id.lin, false);
        }
    }
}
