package com.example.administrator.demo.adapter;

import android.content.Context;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.SCBean;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.ArrayList;

/**
 * 评论
 **/
public class CommentAdapter extends BaseQuickAdapter<SCBean.BizCircleBean, BaseViewHolder> {
    private final Context context;
    private boolean showCheck;

    public CommentAdapter(Context context, ArrayList<SCBean.BizCircleBean> beanList) {
        super(R.layout.item_comment, beanList);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SCBean.BizCircleBean bizCircleBean) {
        helper.setGone(R.id.CheckBox_my_collection, showCheck)
                .setImageResource(R.id.CheckBox_my_collection, bizCircleBean.getIsDetele() ? R.drawable.check_ok : R.drawable.check_no)
                .addOnClickListener(R.id.CheckBox_my_collection)
                .setText(R.id.tv_name, bizCircleBean.getUserInfo().getNickName())
                .setText(R.id.tv_vip, bizCircleBean.getUserInfo().getVipLevel())
                .setText(R.id.tv_time, bizCircleBean.getCreateTime())
                .setText(R.id.tv_content, bizCircleBean.getContent())
                .setText(R.id.tv_zan, bizCircleBean.getPraiseNum())
                .setText(R.id.tv_pinglun, bizCircleBean.getCommentNum())
                .setText(R.id.tv_content, bizCircleBean.getContent())
        ;
        ImageLoader.getInstance().loadingImage(mContext, bizCircleBean.getUserInfo().getHeadPortrait(), helper.getView(R.id.iv_title),
                new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.defaulthead);
    }


    public void setShowCheck(boolean showCheck) {
        this.showCheck = showCheck;
        notifyDataSetChanged();
    }

    public boolean getShowCheck() {
        return showCheck;
    }
}
