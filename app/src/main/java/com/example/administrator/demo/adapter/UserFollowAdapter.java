package com.example.administrator.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.UnFollowBen;
import com.example.administrator.demo.weight.AppActivityUtils;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.ArrayList;


/**
 * 关注/粉丝
 **/
public class UserFollowAdapter extends CommonAdapter<UnFollowBen.RelationRecordListBean> {

    private Context context;


    public UserFollowAdapter(Context context, ArrayList<UnFollowBen.RelationRecordListBean> beanList) {
        super(context, R.layout.item_follow, beanList);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, UnFollowBen.RelationRecordListBean userFollowBen, int position) {
        holder.setText(R.id.tv_name, userFollowBen.getUserName())
                .setText(R.id.tv1, userFollowBen.getUserSignature())
                .setOnClickListener(R.id.fl_content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppActivityUtils.StartTheActivity((Activity) mContext, "他的资料");
                    }
                })
                .setOnClickListener(R.id.iv_r, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null)
                            mOnItemClickListener.onItemClick(v, holder, position);
                    }
                })
        ;
        ImageLoader.getInstance().loadingImage(mContext, userFollowBen.getUserPhoto(), holder.getView(R.id.iv_imageView),
                new MultiTransformation(new CircleCrop()), R.drawable.defaulthead);

        if ("0".equals(userFollowBen.getRalationType())) {
            holder.setImageResource(R.id.iv_r, R.mipmap.weiguanzhu);
        } else if ("1".equals(userFollowBen.getRalationType())) {
            holder.setImageResource(R.id.iv_r, R.drawable.icon_follow);
        } else if ("2".equals(userFollowBen.getRalationType())) {
            holder.setImageResource(R.id.iv_r, R.drawable.icon_hxfollow);
        } else {
            holder.setImageResource(R.id.iv_r, R.mipmap.weiguanzhu);
        }

    }

    public void setData(ArrayList<UnFollowBen.RelationRecordListBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }
}
