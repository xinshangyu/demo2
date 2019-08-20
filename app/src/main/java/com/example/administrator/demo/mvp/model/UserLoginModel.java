package com.example.administrator.demo.mvp.model;

import android.content.Context;
import android.util.ArrayMap;

import com.example.administrator.demo.api.Address;
import com.example.administrator.demo.api.ApiKeys;
import com.example.administrator.demo.entity.UserLogInBen;
import com.example.administrator.demo.mvp.presenter.UUserLoginPresenter;
import com.example.administrator.demo.network.RetrofitRequest;
import com.example.administrator.demo.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.google.gson.Gson;

import java.util.Map;


public class UserLoginModel {
    private UUserLoginPresenter uUserLoginPresenter;
    private Map<String, String> paramMap;
    private UserLogInBen.DataBean.UserCodeBean userCodeBea;

    public UserLoginModel(UUserLoginPresenter uUserLoginPresenter) {

        this.uUserLoginPresenter = uUserLoginPresenter;
    }

    public void getServerRanking(Context context, String userPhone, String userPasswork) {

        paramMap = new ArrayMap<>();
        paramMap.put("userCode", userPhone);
        paramMap.put("userPwd", userPasswork);
        paramMap.put("phoneMac", "aa:aa;aa");
        paramMap.put("phoneModel", "huawei");
        paramMap.put("appVersions", "1.0.0");
        paramMap.put("logType", "1");
        paramMap.put("deviceType", "2");
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.userIogin, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("登录" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 2021) {
                    UserLogInBen data = new Gson().fromJson(new Gson().toJson(weatherResult), UserLogInBen.class);
                    userCodeBea = data.getData().getUserCode();
                    SharedPreferencesHelper.setPrefString("userCode", userCodeBea.getUserCode());
                    SharedPreferencesHelper.setPrefString("userPwd", userCodeBea.getUserPwd());
                    SharedPreferencesHelper.setPrefString("userId", userCodeBea.getId());
                    SharedPreferencesHelper.setPrefString("advertsiongId", String.valueOf(data.getData().getAdInfo().get(0).getId()));//广告ID
                    SharedPreferencesHelper.setPrefString("token", data.getData().getToken());
                    //处理完给前端
                    uUserLoginPresenter.toRegister(weatherResult.getCode(), weatherResult.getMsg(), Integer.parseInt(userCodeBea.getIsFirstLogin()));
                    return;
                }

                uUserLoginPresenter.toRegister(weatherResult.getCode(), weatherResult.getMsg(), 0);
            }

            @Override
            public void onAfterFailure() {

            }
        });
    }

}
