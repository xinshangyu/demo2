package com.example.administrator.demo;


import com.example.administrator.demo.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {


        //测试ldh？？？
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

    }

}
