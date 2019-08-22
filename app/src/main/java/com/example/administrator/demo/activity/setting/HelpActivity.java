package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 客服中心
 */
public class HelpActivity extends BaseActivity {

    @BindView(R.id.common_toolBar_image_right)
    ImageButton commonToolBarImageRight;

    @Override
    protected int getLayout() {
        return R.layout.activity_call;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.help), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, true, getResources().getString(R.string.feedback));
    }

    @Override
    protected void initDate() {

    }

    @OnClick(R.id.common_toolBar_image_right)
    public void onClick() {
        showToast("==========TODO=======");
    }
}
