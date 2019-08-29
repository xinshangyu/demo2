package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.JfgzDetailsAdapter;
import com.example.administrator.demo.entity.JFGZBean;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 积分规则
 */
public class JfGzActivity extends BaseActivity implements CommonView {

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;

    private JfgzDetailsAdapter mAdapter;
    private ArrayList<JFGZBean.ProtocolRulesBean> mBeanList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_fund_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.jfgz), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new JfgzDetailsAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initDate() {
        cMap.put("protocolType", "2");
        cPresenter.requestDataJF(this, cMap, Address.pointsRules);

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        JFGZBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), JFGZBean.class);
        if (sqBean != null && sqBean.getProtocolRules() != null) {

            mBeanList.add(sqBean.getProtocolRules());
            mAdapter.notifyDataSetChanged();
        }

    }
}
