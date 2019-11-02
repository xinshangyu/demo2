package com.example.administrator.demo;

import android.app.Application;

import com.example.administrator.demo.weight.ApplicationUtils;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.CommonBaseLibrary;
import com.example.baselibrary.zh.api.ApiKeys;

public class AppApplication extends Application {
    public static Application mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        new SharedPreferencesHelper(getApplicationContext());
        CommonBaseLibrary.getInstance().init(mApplication, ApiKeys.getApiUrl());
        ApplicationUtils.init(this);
    }
}
