package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

/**
 * 绑定邮箱成功
 */
public class BindEmailSuccessActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_email_success;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.bind_email), new View.OnClickListener() {
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
