package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 账号与安全
 */
public class AccountActivity extends BaseActivity {

    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.rl_tv_nick_update)
    RelativeLayout rlTvNickUpdate;
    @BindView(R.id.rl_tv_update_pwd)
    RelativeLayout rlTvUpdatePwd;
    @BindView(R.id.rl_tv_bing_email)
    RelativeLayout rlTvBingEmail;

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.account), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {


    }

    @OnClick({R.id.rl_tv_nick_update, R.id.rl_tv_update_pwd, R.id.rl_tv_bing_email})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_tv_nick_update:
                ActivityUtils.startActivity(mContext, UpdatePhoneActivity.class);
                break;
            case R.id.rl_tv_update_pwd:
                ActivityUtils.startActivity(mContext, UpdatePwdActivity.class);
                break;
            case R.id.rl_tv_bing_email:
                break;
        }
    }
}
