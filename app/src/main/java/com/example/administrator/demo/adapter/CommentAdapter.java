package com.example.administrator.demo.adapter;

import android.content.Context;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.CommertListBen;
import com.example.administrator.demo.entity.SCBean;
import com.example.administrator.demo.entity.UserFollowBen;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;
import com.example.baselibrary.zh.utils.GlideCircleTransform;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 **/
public class CommentAdapter extends CommonAdapter<SCBean.BizCircleBean> {


    private boolean showCheck;

    public CommentAdapter(Context context, ArrayList<SCBean.BizCircleBean> beanList) {
        super(context, R.layout.item_comment, beanList);
    }

    public void setShowCheck(boolean showCheck) {
        this.showCheck = showCheck;
        notifyDataSetChanged();
    }
    @Override
    protected void convert(ViewHolder holder, SCBean.BizCircleBean bizCircleBean, int position) {

        holder.setVisible(R.id.CheckBox_my_collection, showCheck)
                .setText(R.id.tv_name, bizCircleBean.getUserInfo().getNickName())
                .setText(R.id.tv_vip, bizCircleBean.getUserInfo().getVipLevel())
                .setText(R.id.tv_time, bizCircleBean.getCreateTime())
                .setText(R.id.tv_content, bizCircleBean.getContent())
                .setText(R.id.tv_zan, bizCircleBean.getPraiseNum())
                .setText(R.id.tv_content, bizCircleBean.getCommentNum())
        ;
        ImageLoader.getInstance().loadingImage(mContext, bizCircleBean.getUserInfo().getHeadPortrait(), holder.getView(R.id.iv_title),
                new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.defaulthead);

    }
}
