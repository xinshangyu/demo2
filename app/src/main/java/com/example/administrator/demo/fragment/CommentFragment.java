package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.CommentAdapter;
import com.example.administrator.demo.adapter.CommentAdapter2;
import com.example.administrator.demo.adapter.UserFollowAdapter;
import com.example.administrator.demo.entity.CommertListBen;
import com.example.administrator.demo.entity.UserFollowBen;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseFragment;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 评论
 */
public class CommentFragment extends BaseFragment implements RefreshCallBack, CommonView{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    CommentAdapter2 mAdapter;
    private ArrayList<CommertListBen.BizCircleBean> mBeanList = new ArrayList<>();

    public static CommentFragment newInstance(String param1, String param2) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_follows;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
//        if (isVisible) setStatusBarColorInFragment();
        if (isVisible) mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentFirstVisible() {
//        for (int i = 0; i < 5; i++) {
//            mBeanList.add("不错电视剧" + i);
//        }
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cMap.put("oprType", "05");//阅读
        cPresenter.requestData2(getActivity(), cMap, Address.commertArtiles);
        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CommentAdapter2(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        CommertListBen commertListBen = gson.fromJson(gson.toJson(weatherResult.getData()), CommertListBen.class);
        if(commertListBen != null && commertListBen.getBizCircle() != null && commertListBen.getBizCircle().size() >0){
            List<CommertListBen.BizCircleBean> bizCircle = commertListBen.getBizCircle();
            mBeanList.addAll(bizCircle);
            mAdapter.notifyDataSetChanged();
        }
    }
}
