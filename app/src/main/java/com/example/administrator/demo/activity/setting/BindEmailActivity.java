package com.example.administrator.demo.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

/**
 * 绑定邮箱
 */
public class BindEmailActivity extends BaseActivity {

    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private Map<String, String> paramMap;

    @Override
    protected int getLayout() {
        return R.layout.activity_email_bind;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.bind_email), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {


    }


    @OnClick({R.id.tv_code, R.id.tv_save})
    public void onViewClicked(View view) {
        String phone = etNumber.getText().toString();
        String code = etCode.getText().toString();
        switch (view.getId()) {
            case R.id.tv_code:
                if (TextUtils.isEmpty(phone)) {// TODO: 2019/8/29 这里需要验证邮箱正确性，目前没验证，后台要验证
                    showToast(R.string.login_email_error_hint);
                } else {
                    // TODO: 2019/8/21 调用接口
                }

                break;
            case R.id.tv_save:
                if (TextUtils.isEmpty(phone)) {// TODO: 2019/8/29 这里需要验证邮箱正确性，目前没验证，后台要验证
                    showToast(R.string.login_email_error_hint);
                    return;
                } else if (TextUtils.isEmpty(code)) {
                    showToast(R.string.login_code_error_hint);
                    return;
                } else {
                    updateEmail(mContext, phone, code);
                }
                break;
        }
    }

    public void updateEmail(Context context, String userPhone, String userCodeBea) {

        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("userMail", userPhone);
        paramMap.put("smsCount", userCodeBea);
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.update_number_zh, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                if (weatherResult.getCode() == 200) {
                    ActivityUtils.startActivity(mContext, UpdateEmailSuccessActivity.class);
                    finish();
                }

            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });
    }
}
