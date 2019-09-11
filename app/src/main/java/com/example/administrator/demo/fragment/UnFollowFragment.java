package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.UserFollowAdapter;
import com.example.administrator.demo.entity.UnFollowBen;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.adapter.MultiItemTypeAdapter;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseFragment;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 粉丝
 */
public class UnFollowFragment extends BaseFragment implements RefreshCallBack, CommonView, MultiItemTypeAdapter.OnItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rl_empty;

    private UserFollowAdapter mAdapter;
    private ArrayList<UnFollowBen.RelationRecordListBean> mBeanList = new ArrayList<>();
    private String ralationType;
    private Map<String, String> paramMap;

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
        Map<String, String> map = new HashMap<>();
        map.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData(getActivity(), map, Address.payAttentionToFans);

        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new UserFollowAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        UnFollowBen unFollowBen = new Gson().fromJson(new Gson().toJson(weatherResult.getData()), UnFollowBen.class);
        if (unFollowBen != null && unFollowBen.getRelationRecordList().size() > 0) {
            rl_empty.setVisibility(View.GONE);
            List<UnFollowBen.RelationRecordListBean> data = (ArrayList<UnFollowBen.RelationRecordListBean>) unFollowBen.getRelationRecordList();
            mBeanList.addAll(data);
            mAdapter.notifyDataSetChanged();
        } else {
            rl_empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError() {
        if (rl_empty != null) rl_empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        UnFollowBen.RelationRecordListBean relationRecordListBean = mBeanList.get(position);
        String fansId = relationRecordListBean.getFansId();
        String ralationType = relationRecordListBean.getRalationType();
        if ("0".equals(ralationType)) {
            ralationType = "1";
        } else if ("1".equals(ralationType)) {
            ralationType = "0";
        }

        initData(fansId, ralationType);
    }

    /**
     * 按钮接口
     *
     * @param fansId
     * @param ralationType
     */
    private void initData(String fansId, String ralationType) {
        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("ralationType", ralationType);
        paramMap.put("fansId", fansId);
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.attention, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                String json = new Gson().toJson(weatherResult);
                LogUtil.e("返回数据" + json);
                if (weatherResult.getCode() == 200) {

                } else {
                    showToast("" + weatherResult.getMsg());
                }

            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });

    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
