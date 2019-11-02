package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.weight.AppActivityUtils;
import com.example.baselibrary.zh.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 更新手机号成功
 */
public class UpdatePhoneSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected int getLayout() {
        return R.layout.activity_phone_success;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.update_number), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {

    }

    @OnClick(R.id.tv_save)
    public void onClick() {
        // TODO: 2019/8/21 重新登陆
        AppActivityUtils.StartLoginTaskActivity(mContext);
    }
}
