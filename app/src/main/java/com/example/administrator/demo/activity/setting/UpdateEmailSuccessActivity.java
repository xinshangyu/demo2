package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改邮箱成功
 */
public class UpdateEmailSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_nick)
    TextView tvNick;

    @Override
    protected int getLayout() {
        return R.layout.activity_email_success;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.update_email), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {
        tvCode.setText(getString(R.string.email_success3));
        tvNick.setVisibility(View.GONE);

    }

}
