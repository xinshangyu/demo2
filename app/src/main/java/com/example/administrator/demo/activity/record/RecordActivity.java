package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.FundDetailsAdapter;
import com.example.administrator.demo.entity.DhBean;
import com.example.administrator.demo.entity.JyjlBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 交易记录
 */
public class RecordActivity extends BaseActivity implements CommonView,RefreshCallBack {

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private FundDetailsAdapter mAdapter;
    private ArrayList<JyjlBean.VirtualCurrencyRecordBean> mBeanList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_fund_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        setTitleBar(getResources().getString(R.string.record), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new FundDetailsAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData2(getApplicationContext(), cMap, Address.txnRecord);

    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {
        initDate();
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        JyjlBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), JyjlBean.class);
        if (sqBean != null && sqBean.getVirtualCurrencyRecord() != null && sqBean.getVirtualCurrencyRecord().size() > 0) {
            mBeanList.addAll(sqBean.getVirtualCurrencyRecord());
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError() {

    }
}
