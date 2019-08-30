package com.example.administrator.demo.activity.vip;

import android.os.Bundle;

import com.example.administrator.demo.R;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;

/**
 * 我的vip
 */
public class MyVipActivity extends BaseActivity implements CommonView {


    @Override
    protected int getLayout() {
        return R.layout.activity_my_vip;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData(this, cMap, Address.vip_getVipEquities);

    }


    @Override
    public void onData(WeatherResult weatherResult) {

    }
}
