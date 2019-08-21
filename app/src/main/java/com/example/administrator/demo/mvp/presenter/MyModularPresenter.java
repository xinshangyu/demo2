package com.example.administrator.demo.mvp.presenter;

import android.content.Context;

import com.example.administrator.demo.mvp.iview.MyModularView;
import com.example.administrator.demo.mvp.model.MyModularModel;
import com.example.administrator.demo.mvp.presenter.basePresenter.IMyModularPresenter;
import com.example.administrator.demo.mvp.presenter.basePresenter.UUserLoginPresenter;
import com.example.administrator.demo.network.result.WeatherResult;


public class MyModularPresenter implements IMyModularPresenter {

    private MyModularView myModularView;
    private MyModularModel myModularModel;

    public MyModularPresenter(MyModularView myModularView) {

        this.myModularView = myModularView;
        myModularModel = new MyModularModel(this);
    }



    //开始处理
    public void requestRanking(Context context) {

        myModularModel.getUserInfo(context);
    }

    @Override
    public void toMyModular(WeatherResult weatherResult) {
        myModularView.onMyModular(weatherResult);
    }


}
