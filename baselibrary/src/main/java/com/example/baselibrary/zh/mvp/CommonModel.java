package com.example.baselibrary.zh.mvp;

import android.content.Context;

import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.ToastUtils;
import com.google.gson.Gson;

import java.util.Map;


public class CommonModel {
    private CommonPresenter commonPresenter;

    public CommonModel(CommonPresenter commonPresenter) {
        this.commonPresenter = commonPresenter;
    }

    public void getData(final Context context, Map<String, String> paramMap, String url) {


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
