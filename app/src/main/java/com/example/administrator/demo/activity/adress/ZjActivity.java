package com.example.administrator.demo.activity.adress;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.TrackAdapter;
import com.example.administrator.demo.entity.TrackBean;
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
 * 足迹
 */
public class ZjActivity extends BaseActivity implements RefreshCallBack, CommonView {

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    TrackAdapter mAdapter;
    private ArrayList<TrackBean.FootprintBean> mBeanList = new ArrayList<>();
    private TrackBean.FootprintBean footprintBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_track;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.zj), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setRefresh(mSmartRefreshLayout, this);
        footprintBean = new TrackBean.FootprintBean();
        footprintBean.setAdd(true);
        footprintBean.setFootprintImgSrc(R.drawable.icon_zj_add + "");
        footprintBean.setFootprintName("");
        mBeanList.add(footprintBean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new TrackAdapter(mBeanList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TrackBean.FootprintBean bean = (TrackBean.FootprintBean) adapter.getItem(position);
                if(bean.isAdd()){

                }
            }
        });

    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestDataZJ(this, cMap, Address.footprint_list);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        TrackBean trackBean = gson.fromJson(gson.toJson(weatherResult.getData()), TrackBean.class);
        if(trackBean != null && trackBean.getFootprint() != null && trackBean.getFootprint().size() > 0){
            mBeanList.clear();
            mBeanList.addAll(0, trackBean.getFootprint());
            mBeanList.add(footprintBean);
            mAdapter.notifyDataSetChanged();
        }
    }
}
