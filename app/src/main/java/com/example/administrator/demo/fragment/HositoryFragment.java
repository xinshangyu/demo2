package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.MyLisiAdapter;
import com.example.administrator.demo.adapter.MyReadAdapter;
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
 * 历史成绩
 */
public class HositoryFragment extends BaseFragment implements RefreshCallBack {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private MyLisiAdapter mAdapter;
    private ArrayList<String> mBeanList = new ArrayList<>();

    public static My_ReadFragment newInstance(String param1, String param2) {
        My_ReadFragment fragment = new My_ReadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_my_read;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
//        if (isVisible) mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentFirstVisible() {

//        for (int i = 0; i < 10; i++) {
//            mBeanList.add("看啥");
//        }
//
//        setRefresh(mSmartRefreshLayout, this);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
//        mAdapter = new MyLisiAdapter(mContext, mBeanList);
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {
        setFinishRefresh(mSmartRefreshLayout, false);//
    }
}
