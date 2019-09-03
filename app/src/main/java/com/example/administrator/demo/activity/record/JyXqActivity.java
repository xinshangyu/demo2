package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.Help2Bean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;

import butterknife.BindView;

/**
 * 问题帮助
 */
public class JyXqActivity extends BaseActivity {

    private String content;

    @Override
    protected int getLayout() {
        return R.layout.item_message_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        content = getIntent().getStringExtra("CONTENT");

        setTitleBar(getResources().getString(R.string.jyxq), new View.OnClickListener() {
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
