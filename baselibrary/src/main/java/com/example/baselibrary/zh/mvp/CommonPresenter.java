package com.example.baselibrary.zh.mvp;

import android.content.Context;

import com.example.baselibrary.zh.network.result.WeatherResult;

import java.util.Map;


public class CommonPresenter implements ICommonPresenter {

    private CommonView commonView;
    private CommonModel commonModel;

    public CommonPresenter(CommonView commonView) {
        this.commonView = commonView;
        commonModel = new CommonModel(this);
    }


    //开始处理
    public void requestData(Context context, Map<String, String> map, String url) {

        commonModel.getData(context, map, url);
    }

    //开始处理
    public void requestData2(Context context, Map<String, String> map, String url) {
        commonModel.getData2(context, map, url);
    }

    //开始处理
    public void requestData3(Context context, Map<String, String> map, String url) {
        commonModel.getData3(context, map, url);
    }

    //商圈
    public void requestDataSQ(Context context, Map<String, String> map, String url) {
        commonModel.getData2(context, map, url);
    }
    //足迹
    public void requestDataZJ(Context context, Map<String, String> map, String url) {
        commonModel.getData3(context, map, url);
    }

    //帮助中心客服信息
    public void requestDataKF(Context context, Map<String, String> map, String url) {
        commonModel.getData3(context, map, url);
    }
    //帮助中心faq信息
    public void requestDataFAQ(Context context, Map<String, String> map, String url) {
        commonModel.getData3(context, map, url);
    }

    @Override
    public void toData(WeatherResult weatherResult) {
        commonView.onData(weatherResult);
    }
}
