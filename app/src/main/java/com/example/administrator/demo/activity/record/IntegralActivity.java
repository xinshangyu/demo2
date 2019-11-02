package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.IntegralDetailsAdapter;
import com.example.administrator.demo.entity.IntegralBean;
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
 * 积分兑换记录
 */
public class IntegralActivity extends BaseActivity implements RefreshCallBack, CommonView {

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private IntegralDetailsAdapter mAdapter;
    private ArrayList<IntegralBean.IntegralRecordBean> mBeanList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_fund_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar("兑换记录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new IntegralDetailsAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData2(getApplicationContext(), cMap, Address.exchangeRecords);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {
        setFinishRefresh(mSmartRefreshLayout, false);
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        IntegralBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), IntegralBean.class);
        if (sqBean != null && sqBean.getIntegralRecord() != null && sqBean.getIntegralRecord().size() > 0) {
            mBeanList.addAll(sqBean.getIntegralRecord());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError() {
        showToast("请求失败");
    }
}
