package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class UpdatePwdActivity extends BaseActivity {

    @BindView(R.id.et_old_number)
    EditText etOldNumber;
    @BindView(R.id.et_new_number)
    EditText etNewNumber;
    @BindView(R.id.et_new_number2)
    EditText etNewNumber2;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected int getLayout() {
        return R.layout.activity_pwd_update;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.update_pwd), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {


    }

    @OnClick({R.id.ll_pwd, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_pwd:
                showToast("开发中...");
                break;
            case R.id.tv_save:
                // TODO: 2019/8/21 修改密码
                break;
        }
    }
}
