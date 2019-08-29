package com.example.administrator.demo.activity.adress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
import com.shehuan.nicedialog.BaseNiceDialog;
import com.shehuan.nicedialog.NiceDialog;
import com.shehuan.nicedialog.ViewConvertListener;
import com.shehuan.nicedialog.ViewHolder;

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
        footprintBean.setFootprintImgSrc(R.mipmap.icon_zj_add + "");
        footprintBean.setFootprintName("");
        mBeanList.add(footprintBean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new TrackAdapter(mBeanList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.iv || view.getId() == R.id.iv1){
                    TrackBean.FootprintBean bean = (TrackBean.FootprintBean) adapter.getItem(position);
                    if(bean.isAdd()){
                        NiceDialog.init()
                                .setLayoutId(R.layout.dialog_zuji_show)
                                .setConvertListener(new ViewConvertListener() {
                            @Override
                            protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                final int[] i = {1};
                                TextView textView = holder.getView(R.id.tv_num);

                                holder.setOnClickListener(R.id.iv_less, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(i[0] > 1){
                                            i[0]--;
                                        }
                                        holder.setText(R.id.tv_content, 2000*i[0] + "");
                                        textView.setText(i[0] + "");
                                    }
                                });

                                holder.setOnClickListener(R.id.iv_add, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        i[0]++;
                                        textView.setText(i[0] + "");
                                        holder.setText(R.id.tv_content, 2000*i[0] + "");
                                    }
                                });
                                holder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(ZjActivity.this, ZjAddActivity.class));
                                        dialog.dismiss();
                                    }
                                });

                            }
                        }).show(getSupportFragmentManager());
                    }
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
