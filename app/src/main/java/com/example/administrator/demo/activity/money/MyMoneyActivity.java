package com.example.administrator.demo.activity.money;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的钱包
 */
public class MyMoneyActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.rl_yhj)
    RelativeLayout rlYhj;
    @BindView(R.id.rl_daoju)
    RelativeLayout rlDaoju;
    @BindView(R.id.rl_jyjl)
    RelativeLayout rlJyjl;

    @Override
    protected int getLayout() {
        return R.layout.activity_money;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.money), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {


    }

    @OnClick({R.id.tv_save, R.id.rl_yhj, R.id.rl_daoju, R.id.rl_jyjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                // TODO: 2019/8/21 充值 
                break;
            case R.id.rl_yhj:
                break;
            case R.id.rl_daoju:
                break;
            case R.id.rl_jyjl:
                break;
        }
    }
}
