package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.DhAdapter;
import com.example.administrator.demo.entity.DhBean;
import com.example.administrator.demo.entity.JBean;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 积分商城
 */
public class IntegralShappingActivity extends BaseActivity implements CommonView {

    @BindView(R.id.iv_my_head)
    ImageView ivMyHead;
    @BindView(R.id.ll_jf1)
    LinearLayout llJf1;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;
    @BindView(R.id.ll_gz)
    LinearLayout llGz;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv)
    TextView tv;

    private ArrayList<DhBean.DataBean.MallCommodityBean> mBeanList = new ArrayList<>();
    private DhAdapter adapter;
    private Map<String, String> paramMap;

    @Override
    protected int getLayout() {
        return R.layout.activity_integral;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.jf_sc), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DhAdapter(mContext, mBeanList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initDate() {
        cPresenter.requestDataSelectedFor(this, cMap, Address.selectedFor);

        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.personalPoints, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                String json = new Gson().toJson(weatherResult);
                LogUtil.e("返回数据" + json);
                if (weatherResult.getCode() == 200) {
                    JBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), JBean.class);
                    if (sqBean != null) {
                        double integralNumber = sqBean.getIntegralNumber();
                        tv.setText("" + integralNumber);
                    }
//                    try {
//                        JSONObject jsonObject = new JSONObject(json);
//                        int name = jsonObject.optInt("integralNumber");
//
//                        showToast(""+name);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }

            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });

    }

    @OnClick({R.id.ll_jf1, R.id.ll_detail, R.id.ll_gz, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_jf1:
                break;
            case R.id.ll_detail:
                ActivityUtils.startActivity(mContext, IntegralActivity.class);
                break;
            case R.id.ll_gz:
                ActivityUtils.startActivity(mContext, JfGzActivity.class);
                break;
            case R.id.tv_save:
                showToast("去商城");
                break;
        }
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        DhBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), DhBean.class);
        if (sqBean != null && sqBean.getData() != null && sqBean.getData().getMallCommodity().size() > 0) {
            mBeanList.addAll(sqBean.getData().getMallCommodity());
            adapter.notifyDataSetChanged();
        }
    }
}
