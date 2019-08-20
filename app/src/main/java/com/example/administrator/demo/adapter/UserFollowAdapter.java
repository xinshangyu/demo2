package com.example.administrator.demo.adapter;

import android.content.Context;
import android.net.Uri;


import com.example.administrator.demo.R;
import com.example.administrator.demo.base.BaseAdapterParent;
import com.example.administrator.demo.base.ViewHolder;
import com.example.administrator.demo.entity.UserFollowBen;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


/**
 * 关注/粉丝
 **/
public class UserFollowAdapter extends BaseAdapterParent<UserFollowBen> {
    public UserFollowAdapter(List<UserFollowBen> mDatas, Context mContext, int layoutID) {
        super(mDatas, mContext, layoutID);
    }

    @Override
    public void setContent(ViewHolder holder, List<UserFollowBen> mDatas, int position) {
        SimpleDraweeView pic=    holder.getView(R.id.iv_personal_user_pic);//图片
        pic.setImageURI(Uri.parse(mDatas.get(position).getPic()));
        holder.setText(R.id.lv_user_follow_name, mDatas.get(position).getName());
        holder.setText(R.id.lv_user_follow_qm, mDatas.get(position).getQianming());

    }
}
