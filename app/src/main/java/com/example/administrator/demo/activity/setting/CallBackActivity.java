package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class CallBackActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.feedback), new View.OnClickListener() {
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
