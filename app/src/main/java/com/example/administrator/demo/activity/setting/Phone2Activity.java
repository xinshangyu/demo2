package com.example.administrator.demo.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.CodeBean;
import com.example.administrator.demo.utils.SmsTimeUtils;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class Phone2Activity extends BaseActivity {

    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private String code;
    private String phone;
    private String integralNumber;

    private String content;
    private String oldPhone;
    private String id;
    private Map<String, String> paramMap;

    @Override
    protected int getLayout() {
        return R.layout.activity_phone2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        content = getIntent().getStringExtra("CONTENT");
        oldPhone = getIntent().getStringExtra("PHONE");
        id = getIntent().getStringExtra("ID");
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


    @OnClick({R.id.et_number, R.id.et_code, R.id.tv_code, R.id.tv_save})
    public void onClick(View view) {
        phone = etNumber.getText().toString();
        code = etCode.getText().toString();
        switch (view.getId()) {
            case R.id.tv_code:
                if (TextUtils.isEmpty(phone)) {
                    showToast(R.string.login_phone_error_hint);
                } else {
                    // TODO: 2019/8/21 先唯一校验

                    updatePhoneCodeOne(mContext, phone);


                }
                break;
            case R.id.tv_save:
                if (TextUtils.isEmpty(phone)) {
                    showToast(R.string.login_phone_error_hint);
                    return;
                } else if (TextUtils.isEmpty(code)) {
                    showToast(R.string.login_code_error_hint);
                    return;
                } else {
                    //校验验证码

                    updatePhoneCode2();

                }
                break;
        }
    }

    private void updatePhoneCodeOne(Context mContext, String phone) {
        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("userPhone", phone);
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.isHavePhone, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(getApplicationContext()) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                if (weatherResult.getCode() == 200) {

                    updatePhoneCode(mContext, phone);

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

    private void updatePhoneCode(Context mContext, String phone) {
        paramMap = new HashMap<>();
        paramMap.put("userPhone", phone);
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("smsCode", "SMS_173696406");

        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.sendSmsCode, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("ldh" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 200) {
                    CodeBean codeBeanBean = new Gson().fromJson(new Gson().toJson(weatherResult), CodeBean.class);
                    integralNumber = codeBeanBean.getData().getSmsId();

                    showToast(R.string.login_phone_code_success);
                    SmsTimeUtils.check(SmsTimeUtils.SETTING_FINANCE_ACCOUNT_TIME3, false);
                    SmsTimeUtils.startCountdown(tvCode, mContext);


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

    private void updatePhoneCode2() {
        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("userPhone", phone);
        paramMap.put("smsCode", code);
        paramMap.put("originalPhone", oldPhone);
        paramMap.put("smsId", integralNumber);

        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.update_number_zh, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("ldh" + new Gson().toJson(weatherResult));
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
