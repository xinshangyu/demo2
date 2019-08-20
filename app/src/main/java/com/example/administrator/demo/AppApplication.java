package com.example.administrator.demo;

import android.app.Application;

import com.example.baselibrary.SharedPreferencesHelper;

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new SharedPreferencesHelper(getApplicationContext());
    }
}
