package com.example.administrator.demo.mvp.model;

import android.content.Context;
import android.os.Build;
import android.util.ArrayMap;

import com.example.administrator.demo.api.Address;
import com.example.administrator.demo.api.ApiKeys;
import com.example.administrator.demo.mvp.presenter.FansPresenter;
import com.example.administrator.demo.network.RetrofitRequest;
import com.example.administrator.demo.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class FansModel {
    private FansPresenter fansPresenter;
    private Map<String, String> paramMap;

    public FansModel(FansPresenter fansPresenter) {

        this.fansPresenter = fansPresenter;
    }

    public void getFans(Context context) {

        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.payAttentionToFans, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("粉丝" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 1) {
                    fansPresenter.toFans(weatherResult);
                    return;
                }

//                uUserLoginPresenter.toRegister(weatherResult.getCode(), weatherResult.getMsg(), 0);
                fansPresenter.toFans(weatherResult);
            }

            @Override
            public void onAfterFailure() {
            }
        });
    }

}
