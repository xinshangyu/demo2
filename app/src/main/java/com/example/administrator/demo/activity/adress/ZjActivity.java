package com.example.administrator.demo.activity.adress;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

/**
 * 足迹
 */
public class ZjActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_zj;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.zj), new View.OnClickListener() {
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
