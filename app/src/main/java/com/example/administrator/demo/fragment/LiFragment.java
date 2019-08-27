package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.CommentAdapter;
import com.example.administrator.demo.adapter.UserFollowAdapter;
import com.example.administrator.demo.entity.SCBean;
import com.example.administrator.demo.entity.UserFollowBen;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseFragment;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 历史
 */
public class LiFragment extends BaseFragment implements RefreshCallBack,CommonView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    CommentAdapter mAdapter;
    private ArrayList<SCBean.BizCircleBean> mBeanList = new ArrayList<>();


    public static LiFragment newInstance(String param1, String param2) {
        LiFragment fragment = new LiFragment();
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

        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cMap.put("oprType", "05");//收藏
        cPresenter.requestData2(getActivity(), cMap, Address.browerHistoty);
        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CommentAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        SCBean scBean = gson.fromJson(gson.toJson(weatherResult), SCBean.class);
        if(scBean != null && scBean.getBizCircle() != null && scBean.getBizCircle().size() > 0){
            mBeanList.addAll(scBean.getBizCircle());
            mAdapter.notifyDataSetChanged();
        }
    }
}
