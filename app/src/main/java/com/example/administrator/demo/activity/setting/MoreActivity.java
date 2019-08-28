package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

/**
 * 帮助
 */
public class MoreActivity extends BaseActivity {

    private String content;
    private String title;
    @Override
    protected int getLayout() {
        return R.layout.activity_email_bind;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        content = getIntent().getStringExtra("CONTENT");
        title = getIntent().getStringExtra("TITLE");


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
