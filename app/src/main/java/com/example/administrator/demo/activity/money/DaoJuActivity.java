package com.example.administrator.demo.activity.money;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.DaoDetailsAdapter;
import com.example.administrator.demo.entity.DaojuBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 道具
 */

public class DaoJuActivity extends BaseActivity implements RefreshCallBack, CommonView {


    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    DaoDetailsAdapter mAdapter;
    private List<List<DaojuBean.PropsAssetsBean>> mBeanList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_fund_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar("道具", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new DaoDetailsAdapter(mBeanList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData2(getApplicationContext(), cMap, Address.propsAssets);

    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {
        setFinishRefresh(mSmartRefreshLayout, false);
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        DaojuBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), DaojuBean.class);
        if (sqBean != null && sqBean.getPropsAssets() != null && sqBean.getPropsAssets().size() > 0) {
            mBeanList.clear();
            mBeanList.addAll(sqBean.getPropsAssets());
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError() {
        showToast("请求失败");
    }
}
