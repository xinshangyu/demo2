package com.example.administrator.demo.activity.my;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

/**
 * 编辑个人中心
 */
public class UpdateMyInfoActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.mydata), new View.OnClickListener() {
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
