package com.example.administrator.demo.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.VIPBean;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：积分记录
 */
public class VIPAdapter extends BaseQuickAdapter<VIPBean.UserInfoBean.VipEquitiesRuleBean, BaseViewHolder> {


    public VIPAdapter(Context context, @Nullable List<VIPBean.UserInfoBean.VipEquitiesRuleBean> data) {
        super(R.layout.item_vip, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VIPBean.UserInfoBean.VipEquitiesRuleBean item) {
        helper.setText(R.id.tv_date, item.getVipName())
                .setText(R.id.tv_vip, item.getVipCode());
    }
}
