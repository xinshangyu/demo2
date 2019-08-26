package com.example.administrator.demo.adapter;

import android.content.Context;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.UnFollowBen;
import com.example.administrator.demo.entity.UserFollowBen;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.ArrayList;


/**
 * 关注/粉丝
 **/
public class UserFollowAdapter extends CommonAdapter<UnFollowBen.DataBean.UserRelationBean> {

    public UserFollowAdapter(Context context, ArrayList<UnFollowBen.DataBean.UserRelationBean> beanList) {
        super(context, R.layout.item_follow, beanList);
    }


    @Override
    protected void convert(ViewHolder holder, UnFollowBen.DataBean.UserRelationBean userFollowBen, int position) {

        holder.setText(R.id.tv_name, userFollowBen.getUserInfo().getPetName());
        holder.setText(R.id.tv1, userFollowBen.getUserInfo().getUserSignature());
//        holder.setText(R.id.tv2, userFollowBen.getUserInfo().getUserSignature());
        ImageLoader.getInstance().loadingImage(mContext, userFollowBen.getUserInfo().getUserPhoto(), holder.getView(R.id.iv_imageView),
                new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.defaulthead);
        ImageLoader.getInstance().loadingImage(mContext, userFollowBen.getUserInfo().getVipLevel(), holder.getView(R.id.iv_vip),
                new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.defaulthead);
        if ("0".equals(userFollowBen.getRalationType())) {
            holder.setImageResource(R.id.iv_imageView, R.drawable.icon_follow);
        } else if ("1".equals(userFollowBen.getRalationType())) {
            holder.setImageResource(R.id.iv_imageView, R.mipmap.weiguanzhu);
        } else if ("2".equals(userFollowBen.getRalationType())) {
            holder.setImageResource(R.id.iv_imageView, R.drawable.icon_hxfollow);
        }else{
            holder.setImageResource(R.id.iv_imageView, R.mipmap.weiguanzhu);
        }


    }
}
