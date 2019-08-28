package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.CallAdapter;
import com.example.administrator.demo.entity.Help2Bean;
import com.example.administrator.demo.entity.HelpBean;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.adapter.wrapper.EmptyWrapper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 客服中心
 */
public class HelpActivity extends BaseActivity implements CommonView {

    @BindView(R.id.common_toolBar_image_right)
    ImageButton commonToolBarImageRight;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_content2)
    TextView tvContent2;
    @BindView(R.id.tv_content3)
    TextView tvContent3;

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;

    private CallAdapter adapter;
    private EmptyWrapper mAdapter;
    private ArrayList<Help2Bean.FaqRecordBean> mBeanList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_call;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.help), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, true, getResources().getString(R.string.feedback));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new CallAdapter(mContext, mBeanList);
        mAdapter = new EmptyWrapper(adapter);
        mAdapter.setEmptyView(R.layout.empty_view);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestDataKF(getApplicationContext(), cMap, Address.findServiceInfo);

        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl3() + Address.findFAQs, cMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("返回数据" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 200) {
                    Help2Bean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), Help2Bean.class);
                    if (sqBean != null && sqBean.getFaqRecord().size() > 0) {
                        mBeanList.addAll(sqBean.getFaqRecord());
                        mAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onAfterFailure() {

            }
        });

    }

    @OnClick(R.id.common_toolBar_text_right)
    public void onClick(View view) {
        if (view.getId() == R.id.common_toolBar_text_right) {
            ActivityUtils.startActivity(mContext, CallBackActivity.class);
        }

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        HelpBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), HelpBean.class);
        if (sqBean != null) {
            tvContent.setText("" + sqBean.getSysInfo().getOfficalAccounts());
            tvContent2.setText("" + sqBean.getSysInfo().getServiceTel());
            tvContent3.setText("" + sqBean.getSysInfo().getServiceTime());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
