package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class Pwd2Activity extends BaseActivity {

    @BindView(R.id.et_new_number)
    EditText etNewNumber;
    @BindView(R.id.login_password_watch2)
    CheckBox loginPasswordWatch2;
    @BindView(R.id.et_new_number2)
    EditText etNewNumber2;
    @BindView(R.id.login_password_watch3)
    CheckBox loginPasswordWatch3;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private String content;
    private String id;
    private Map<String, String> paramMap;

    @Override
    protected int getLayout() {
        return R.layout.activity_pwd2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        content = getIntent().getStringExtra("CONTENT");
        id = getIntent().getStringExtra("ID");
        setTitleBar("设置密码", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {
        loginPasswordWatch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etNewNumber.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etNewNumber.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etNewNumber.setSelection(etNewNumber.length());
            }
        });
        loginPasswordWatch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etNewNumber2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etNewNumber2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etNewNumber2.setSelection(etNewNumber2.length());
            }
        });
    }


    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        String mEtNewNumber = etNewNumber.getText().toString();
        String mEetNewNumber2 = etNewNumber2.getText().toString();

        if (!mEtNewNumber.equals(mEetNewNumber2)) {
            showToast("请确认两次密码是否一致");
        } else if (TextUtils.isEmpty(mEtNewNumber) || TextUtils.isEmpty(mEetNewNumber2)) {
            showToast("请输入旧密码或新密码");
        } else {
            /**
             * 密码修改
             */
            paramMap = new HashMap<>();
            paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
            paramMap.put("smsId", id);
            paramMap.put("smsCode", content);
            paramMap.put("newPassWord", mEtNewNumber);
            RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.bySmsCode, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(getBaseContext()) {
                @Override
                public void onBeforeResult() {

                }

                @Override
                public void onResult(WeatherResult weatherResult) {
                    if (weatherResult.getCode() == 200) {

                        ActivityUtils.startActivity(mContext, UpdatePwdSuccessActivity.class);
                    } else {
                        showToast("" + weatherResult.getMsg());
                    }
                }

                @Override
                public void onAfterFailure() {
                    showToast("请求失败");
                }
            });
        }
    }
}
