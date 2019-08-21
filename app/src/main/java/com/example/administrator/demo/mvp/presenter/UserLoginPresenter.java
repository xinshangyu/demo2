package com.example.administrator.demo.mvp.presenter;

import android.content.Context;

import com.example.administrator.demo.mvp.iview.UserLoginView;
import com.example.administrator.demo.mvp.model.UserLoginModel;
import com.example.administrator.demo.mvp.presenter.basePresenter.UUserLoginPresenter;


public class UserLoginPresenter implements UUserLoginPresenter {

    private UserLoginView userLoginView;
    private UserLoginModel userLoginModel;

    public UserLoginPresenter(UserLoginView userLoginView) {

        this.userLoginView = userLoginView;
        userLoginModel = new UserLoginModel(this);
    }



    //开始处理
    public void requestRanking(Context context, String userVcode, String userPasswork) {

        userLoginModel.getServerRanking(context, userVcode, userPasswork);
    }

    @Override
    public void toRegister(int state, String msg,int isFirstLogin) {
        userLoginView.onLoginStatus(state, msg,isFirstLogin);
    }
}
