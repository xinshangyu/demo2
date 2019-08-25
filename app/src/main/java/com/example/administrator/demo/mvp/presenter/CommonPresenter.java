package com.example.administrator.demo.mvp.presenter;

import android.content.Context;

import com.example.administrator.demo.mvp.iview.CommonView;
import com.example.administrator.demo.mvp.iview.FansView;
import com.example.administrator.demo.mvp.model.CommonModel;
import com.example.administrator.demo.mvp.model.FansModel;
import com.example.administrator.demo.mvp.presenter.basePresenter.ICommonPresenter;
import com.example.administrator.demo.mvp.presenter.basePresenter.IFansPresenter;
import com.example.administrator.demo.network.result.WeatherResult;

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


    @Override
    public void toData(WeatherResult weatherResult) {
        commonView.onData(weatherResult);
    }
}
