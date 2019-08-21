package com.example.administrator.demo.mvp.model;

import android.content.Context;
import android.util.ArrayMap;

import com.example.administrator.demo.api.Address;
import com.example.administrator.demo.api.ApiKeys;
import com.example.administrator.demo.entity.UserLogInBen;
import com.example.administrator.demo.mvp.presenter.MyModularPresenter;
import com.example.administrator.demo.network.RetrofitRequest;
import com.example.administrator.demo.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.ToastUtils;
import com.google.gson.Gson;

import java.util.Map;


public class MyModularModel {
    private MyModularPresenter myModularPresenter;
    private Map<String, String> paramMap;
    private UserLogInBen.DataBean.UserCodeBean userCodeBea;

    public MyModularModel(MyModularPresenter myModularPresenter) {

        this.myModularPresenter = myModularPresenter;
    }

    public void getUserInfo(Context context) {

        paramMap = new ArrayMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("token", ""));
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.me_data, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("我的" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 2021) {
//                    UserLogInBen data = new Gson().fromJson(new Gson().toJson(weatherResult), UserLogInBen.class);
//                    userCodeBea = data.getData().getUserCode();
//                    SharedPreferencesHelper.setPrefString("userCode", userCodeBea.getUserCode());
//                    SharedPreferencesHelper.setPrefString("userPwd", userCodeBea.getUserPwd());
//                    SharedPreferencesHelper.setPrefString("userId", userCodeBea.getId());
//                    SharedPreferencesHelper.setPrefString("advertsiongId", String.valueOf(data.getData().getAdInfo().get(0).getId()));//广告ID
//                    SharedPreferencesHelper.setPrefString("token", data.getData().getToken());
//                    //处理完给前端
//                    uUserLoginPresenter.toRegister(weatherResult.getCode(), weatherResult.getMsg(), Integer.parseInt(userCodeBea.getIsFirstLogin()));
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
