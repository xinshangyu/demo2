package com.example.administrator.demo;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.administrator.demo.activity.my.MyDataActivity;
import com.example.administrator.demo.activity.my.MyModularActivity;
import com.example.administrator.demo.base.BaseActivity;
import com.example.baselibrary.SharedPreferencesHelper;
import com.google.gson.Gson;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.FileFilter;
import com.vincent.filepicker.filter.callback.FilterResultCallback;
import com.vincent.filepicker.filter.entity.Directory;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    /**
     * 我的模块
     */
    @OnClick(R.id.bt_my_modular)
    void onMyModular() {
        startActivity(MyModularActivity.class, 0);
    }

    /***
     * 我的资料
     */
    @OnClick(R.id.bt_my_data)
    void onMyData() {
        //TODO:根据业务需求添加功能实现
        startActivity(MyDataActivity.class, 0);
    }


}
