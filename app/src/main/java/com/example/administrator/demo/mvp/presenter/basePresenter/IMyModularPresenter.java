package com.example.administrator.demo.mvp.presenter.basePresenter;

import com.example.administrator.demo.network.result.WeatherResult;

public interface IMyModularPresenter {
    void toMyModular(WeatherResult weatherResult);//mode处理返回结果
}
