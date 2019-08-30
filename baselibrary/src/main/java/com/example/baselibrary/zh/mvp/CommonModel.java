package com.example.baselibrary.zh.mvp;

import android.content.Context;

import com.example.baselibrary.LogUtil;
import com.example.baselibrary.ToastUtils;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
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
                LogUtil.e("ldh 返回数据1" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 200) {//code==200不是等于1
                    commonPresenter.toData(weatherResult);
                    return;
                }
            }

            @Override
            public void onAfterFailure() {
                ToastUtils.showShort(context, "请求失败");
            }
        });
    }

    public void getData2(final Context context, Map<String, String> paramMap, String url) {


        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl2() + url, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                String json = new Gson().toJson(weatherResult);
                LogUtil.e("ldh 返回数据2" + json);
                if (weatherResult.getCode() == 200) {//code==200不是等于1
                    commonPresenter.toData(weatherResult);
                    return;
                }
            }

            @Override
            public void onAfterFailure() {
                ToastUtils.showShort(context, "请求失败");
            }
        });
    }

    public void getData3(final Context context, Map<String, String> paramMap, String url) {

        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl3() + url, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                String json = new Gson().toJson(weatherResult);
                LogUtil.e("ldh 返回数据3" + json);
                if (weatherResult.getCode() == 200) {//code==200不是等于1
                    commonPresenter.toData(weatherResult);
                    return;
                }
            }

            @Override
            public void onAfterFailure() {
                ToastUtils.showShort(context, "请求失败");
            }
        });
    }



}
