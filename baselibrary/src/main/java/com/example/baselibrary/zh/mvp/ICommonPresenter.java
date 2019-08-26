package com.example.baselibrary.zh.mvp;

import com.example.baselibrary.zh.network.result.WeatherResult;

public interface ICommonPresenter {
    void toData(WeatherResult weatherResult);//mode处理返回结果
}
