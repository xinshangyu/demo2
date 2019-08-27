package com.example.administrator.demo.activity.personal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.Personal_SQ_Adapter;
import com.example.administrator.demo.entity.SQBean;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.adapter.MultiItemTypeAdapter;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.nicedialog.BaseNiceDialog;
import com.shehuan.nicedialog.NiceDialog;
import com.shehuan.nicedialog.ViewConvertListener;
import com.shehuan.nicedialog.ViewHolder;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;

/**
 * 商圈
 */
public class MyPersonalSQActivity extends BaseActivity implements RefreshCallBack, CommonView, MultiItemTypeAdapter.OnItemClickListener {

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    Personal_SQ_Adapter mAdapter;
    private ArrayList<SQBean.BizCircleBean> mBeanList = new ArrayList<>();
    private Map<String, String> mMap;

    @Override
    protected int getLayout() {
        return R.layout.activity_fund_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new Personal_SQ_Adapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestDataSQ(getApplicationContext(), cMap, Address.bizCircleStatistics);
    }


    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        SQBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), SQBean.class);
        if (sqBean != null && sqBean.getBizCircle().size() > 0) {
            mBeanList.addAll(sqBean.getBizCircle());
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        SQBean.BizCircleBean bizCircleBean = mBeanList.get(position);
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_delete_yc)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.tv_take_photo, new View.OnClickListener() {//编辑
                            @Override
                            public void onClick(View v) {// TODO: 2019/8/27  编辑商圈，看不懂咋编辑
                                dialog.dismiss();

                            }
                        });
                        holder.setOnClickListener(R.id.tv_select_photo, new View.OnClickListener() {//删除
                            @Override
                            public void onClick(View v) {// TODO: 2019/8/27 删除后数据缓存有问题，你看一下 
                                mMap = new ArrayMap<>();
                                mMap.put("id", mBeanList.get(position).getId());
                                mMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));

                                RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl2() + Address.deleteBizCircle, mMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
                                    @Override
                                    public void onBeforeResult() {

                                    }

                                    @Override
                                    public void onResult(WeatherResult weatherResult) {
                                        if (weatherResult.getCode() == 200) {
                                            dialog.dismiss();
                                            initDate();
                                        }
                                    }

                                    @Override
                                    public void onAfterFailure() {

                                    }
                                });


                            }
                        });
                        holder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {//隐藏
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .show(getSupportFragmentManager());
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
