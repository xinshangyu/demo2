package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.UserFollowAdapter;
import com.example.administrator.demo.entity.UnFollowBen;
import com.example.administrator.demo.mvp.iview.FansView;
import com.example.administrator.demo.mvp.presenter.FansPresenter;
import com.example.baselibrary.zh.base.BaseFragment;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 粉丝
 */
public class UnFollowFragment extends BaseFragment implements RefreshCallBack, FansView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private UserFollowAdapter mAdapter;
    private ArrayList<UnFollowBen.DataBean.UserRelationBean> mBeanList = new ArrayList<>();
    private FansPresenter fansPresenter;

    public static UnFollowFragment newInstance(String param1, String param2) {
        UnFollowFragment fragment = new UnFollowFragment();
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
        if (isVisible) mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentFirstVisible() {
        showDialog(getActivity());
        fansPresenter = new FansPresenter(this);
        fansPresenter.requestFans(getActivity());

        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new UserFollowAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onFans(WeatherResult weatherResult) {
        dissDialog(getActivity());
        UnFollowBen unFollowBen = new Gson().fromJson(new Gson().toJson(weatherResult), UnFollowBen.class);
        if (unFollowBen != null && unFollowBen.getData().getUserRelation().size() > 0) {
            List<UnFollowBen.DataBean.UserRelationBean> data = (ArrayList<UnFollowBen.DataBean.UserRelationBean>) unFollowBen.getData().getUserRelation();
            mBeanList.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }
}
