package com.example.administrator.demo.activity.vip;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.CommentAdapter;
import com.example.administrator.demo.adapter.VIPAdapter;
import com.example.administrator.demo.entity.VIPBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.GlideRoundTransform;
import com.example.baselibrary.zh.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的vip
 */
public class MyVipActivity extends BaseActivity implements CommonView {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_vip)
    TextView mTvVip;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private VIPAdapter mAdapter;
    private List<VIPBean.UserInfoBean.VipEquitiesRuleBean> mBeanList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_my_vip;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initDate() {

        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData(this, cMap, Address.vip_getVipEquities);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new VIPAdapter(mContext, mBeanList);
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onData(WeatherResult weatherResult) {
        VIPBean vipBean = gson.fromJson(gson.toJson(weatherResult.getData()), VIPBean.class);
        if(vipBean.getUserInfo() != null){
            ImageLoader.getInstance().loadingImage(mContext, vipBean.getUserInfo().getUserPhoto(), ivTitle,
                    new MultiTransformation(new CenterCrop(), new GlideRoundTransform(mContext, 5)), R.drawable.defaulthead);
            mTvName.setText(vipBean.getUserInfo().getNickName());
            mTvVip.setText(vipBean.getUserInfo().getVipCode() + "");
            mTvAdd.setText("立即升级");
            if(vipBean.getUserInfo().getVipEquitiesRule() != null && vipBean.getUserInfo().getVipEquitiesRule().size() > 0){
                mBeanList.addAll(vipBean.getUserInfo().getVipEquitiesRule());
                mAdapter.notifyDataSetChanged();
            }
        }

    }
}
