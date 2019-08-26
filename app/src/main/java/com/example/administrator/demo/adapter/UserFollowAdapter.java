package com.example.administrator.demo.adapter;

import android.content.Context;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.UserFollowBen;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;


/**
 * 关注/粉丝
 **/
public class UserFollowAdapter extends CommonAdapter<UserFollowBen> {

    public UserFollowAdapter(Context context, ArrayList<UserFollowBen> beanList) {
        super(context, R.layout.item_follow, beanList);
    }


    @Override
    protected void convert(ViewHolder holder, UserFollowBen userFollowBen, int position) {

        holder.setText(R.id.tv_name, userFollowBen.getUserRelation().getUserInfo().getPetName());

    }
}
