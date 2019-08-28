package com.example.administrator.demo.mvp.model;

import android.content.Context;

import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.administrator.demo.entity.UserLogInBen;
import com.example.administrator.demo.mvp.presenter.MyModularPresenter;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class MyModularModel {
    private MyModularPresenter myModularPresenter;
    private Map<String, String> paramMap;
    private UserLogInBen.DataBean.UserCodeBean userCodeBea;

    public MyModularModel(MyModularPresenter myModularPresenter) {

        this.myModularPresenter = myModularPresenter;
    }

    public void getUserInfo(Context context) {

        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.index, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("我的" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 0) {
                    myModularPresenter.toMyModular(weatherResult);
                    return;
                }

//                uUserLoginPresenter.toRegister(weatherResult.getCode(), weatherResult.getMsg(), 0);
                myModularPresenter.toMyModular(null);
            }

            @Override
            public void onAfterFailure() {
                ToastUtils.showShort(context, "请求失败");
            }
        });
    }

}
