package com.example.administrator.demo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.DhBean;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.ArrayList;

/**
 * 兑换
 */
public class DhAdapter extends CommonAdapter<DhBean.MallCommodityBean> {

    private Context context;

    public DhAdapter(Context context, ArrayList<DhBean.MallCommodityBean> datas) {
        super(context, R.layout.item_dh, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder,DhBean.MallCommodityBean messageListBean, int position) {
        ImageView imageView = holder.getView(R.id.iv);
        holder.setText(R.id.tv_name, messageListBean.getCommodityName())
                .setText(R.id.tv_content, messageListBean.getUnitPrice());

        ImageLoader.getInstance().loadingImage(mContext, ApiKeys.getApiUrl() + Address.fileId + messageListBean.getCommodityImg(), imageView,
                new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.deful_back);
    }
}
