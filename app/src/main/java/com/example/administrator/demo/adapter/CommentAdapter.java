package com.example.administrator.demo.adapter;

import android.content.Context;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.UserFollowBen;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;


/**
 *评论
 **/
public class CommentAdapter extends CommonAdapter<String> {

    public CommentAdapter(Context context, ArrayList<String> beanList) {
        super(context, R.layout.item_comment, beanList);
    }


    @Override
    protected void convert(ViewHolder holder, String userFollowBen, int position) {

        holder.setText(R.id.tv_name, userFollowBen.toString());

    }
}
