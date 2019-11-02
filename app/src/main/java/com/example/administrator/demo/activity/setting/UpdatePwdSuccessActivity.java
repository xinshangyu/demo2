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
 * 更新密码成功
 */
public class UpdatePwdSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected int getLayout() {
        return R.layout.activity_pwd_success;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.set_pwd6), new View.OnClickListener() {
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
        //重新登陆
        AppActivityUtils.StartLoginTaskActivity(mContext);
    }
}
