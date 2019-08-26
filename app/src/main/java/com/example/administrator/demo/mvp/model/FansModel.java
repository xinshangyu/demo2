package com.example.administrator.demo.mvp.model;

import android.content.Context;

import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.administrator.demo.mvp.presenter.FansPresenter;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
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
                LogUtil.e("ldh 粉丝" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 200) {
                    fansPresenter.toFans(weatherResult);
                    return;
                }

                fansPresenter.toFans(weatherResult);
            }

            @Override
            public void onAfterFailure() {
            }
        });
    }

}
