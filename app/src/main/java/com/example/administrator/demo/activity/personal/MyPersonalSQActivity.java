package com.example.administrator.demo.activity.personal;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.Personal_SQ_Adapter;
import com.example.administrator.demo.entity.SQBean;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.adapter.MultiItemTypeAdapter;
import com.example.baselibrary.zh.adapter.wrapper.EmptyWrapper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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

    private Personal_SQ_Adapter adapter;
    private EmptyWrapper mAdapter;
    private ArrayList<SQBean.BizCircleBean> mBeanList = new ArrayList<>();
    private Map<String, String> mMap;

    @Override
    protected int getLayout() {
        return R.layout.activity_fund_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar("商圈", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new Personal_SQ_Adapter(mContext, mBeanList);
        mAdapter = new EmptyWrapper(adapter);
        mAdapter.setEmptyView(R.layout.empty_view);
        mRecyclerView.setAdapter(mAdapter);
        adapter.setOnItemClickListener(this);
        setRefresh(mSmartRefreshLayout, this);
    }

    @Override
    protected void initDate() {
        cMap.put("userId", ""+SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData2(getApplicationContext(), cMap, Address.bizCircleStatistics);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {
        setFinishRefresh(mSmartRefreshLayout, false);//
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        SQBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), SQBean.class);
        if (sqBean != null && sqBean.getBizCircle() != null && sqBean.getBizCircle().size() > 0) {
            mBeanList.addAll(sqBean.getBizCircle());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError() {

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
                            public void onClick(View v) {//
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    mMap = new ArrayMap<>();
                                }
                                mMap.put("id", mBeanList.get(position).getId());
                                mMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));

                                RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.deleteBizCircle, mMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
                                    @Override
                                    public void onBeforeResult() {

                                    }

                                    @Override
                                    public void onResult(WeatherResult weatherResult) {
                                        if (weatherResult.getCode() == 200) {
                                            dialog.dismiss();
                                            NiceDialog.init()
                                                    .setLayoutId(R.layout.dialog_delete_show)     //先看下效果，布局最后改
                                                    .setConvertListener(new ViewConvertListener() {
                                                        @Override
                                                        protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                                            holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    dialog.dismiss();
                                                                    mBeanList.remove(position);
                                                                    mAdapter.notifyDataSetChanged();

                                                                }
                                                            });
                                                            holder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    dialog.dismiss();
                                                                }
                                                            });
                                                        }
                                                    })
                                                    .show(getSupportFragmentManager());

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
