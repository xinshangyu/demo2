package com.example.administrator.demo.mvp.presenter;

import android.content.Context;

import com.example.administrator.demo.mvp.iview.FansView;
import com.example.administrator.demo.mvp.model.FansModel;
import com.example.administrator.demo.mvp.presenter.basePresenter.IFansPresenter;
import com.example.baselibrary.zh.network.result.WeatherResult;


public class FansPresenter implements IFansPresenter {

    private FansView fansView;
    private FansModel fansModel;

    public FansPresenter(FansView fansView) {

        this.fansView = fansView;
        fansModel = new FansModel(this);
    }


    //开始处理
    public void requestFans(Context context) {

        fansModel.getFans(context);
    }


    @Override
    public void toFans(WeatherResult weatherResult) {
        fansView.onFans(weatherResult);
    }
}
