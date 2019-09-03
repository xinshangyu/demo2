package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

/**
 * 插件
 */
public class ChaJianActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_chajian;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.unit), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {


    }


}
