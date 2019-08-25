package com.example.administrator.demo.mvp.model;

import android.content.Context;

import com.example.administrator.demo.api.Address;
import com.example.administrator.demo.api.ApiKeys;
import com.example.administrator.demo.mvp.presenter.CommonPresenter;
import com.example.administrator.demo.mvp.presenter.FansPresenter;
import com.example.administrator.demo.network.RetrofitRequest;
import com.example.administrator.demo.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class CommonModel {
    private CommonPresenter commonPresenter;

    public CommonModel(CommonPresenter commonPresenter) {
        this.commonPresenter = commonPresenter;
    }

    public void getData(Context context, Map<String, String> paramMap, String url) {

        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + url, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }
            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("返回数据" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 1) {
                    commonPresenter.toData(weatherResult);
                    return;
                }

//                uUserLoginPresenter.toRegister(weatherResult.getCode(), weatherResult.getMsg(), 0);
                commonPresenter.toData(weatherResult);
            }

            @Override
            public void onAfterFailure() {
                ToastUtils.showShort(context, "请求失败");
            }
        });
    }

}
