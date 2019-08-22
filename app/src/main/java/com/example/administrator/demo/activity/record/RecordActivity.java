package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.FundDetailsAdapter;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 交易记录
 */
public class RecordActivity extends BaseActivity implements RefreshCallBack {

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    FundDetailsAdapter mAdapter;
    private ArrayList<String> mBeanList = new ArrayList<>();

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

        for (int i = 0; i < 10; i++) {
            mBeanList.add(i + "采石场");
        }

        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new FundDetailsAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initDate() {


    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {
        initDate();
    }
}
