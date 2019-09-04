package com.example.baselibrary.zh.mvp;


import com.example.baselibrary.zh.network.result.WeatherResult;

/**
 * 用户注册
 **/
public interface CommonView {
    void onData(WeatherResult weatherResult);
    void onError();
}
