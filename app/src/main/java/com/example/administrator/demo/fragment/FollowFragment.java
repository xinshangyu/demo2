package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.UserFollowAdapter;
import com.example.administrator.demo.entity.UnFollowBen;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseFragment;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 关注
 */
public class FollowFragment extends BaseFragment implements RefreshCallBack, CommonView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private UserFollowAdapter mAdapter;
    private ArrayList<UnFollowBen.DataBean.UserRelationBean> mBeanList = new ArrayList<>();


    public static FollowFragment newInstance(String param1, String param2) {
        FollowFragment fragment = new FollowFragment();
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
//            UserFollowBen userFollowBen = new UserFollowBen();
//            userFollowBen.setName("看啥");
//            mBeanList.add(userFollowBen);
//        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData(getActivity(), map, Address.follow);
        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new UserFollowAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        UnFollowBen unFollowBen = new Gson().fromJson(new Gson().toJson(weatherResult), UnFollowBen.class);
        if (unFollowBen != null && unFollowBen.getData().getUserRelation() != null && unFollowBen.getData().getUserRelation().size() > 0) {
            List<UnFollowBen.DataBean.UserRelationBean> data = unFollowBen.getData().getUserRelation();
            mBeanList.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }
}
