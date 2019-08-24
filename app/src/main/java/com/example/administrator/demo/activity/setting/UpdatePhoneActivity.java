package com.example.administrator.demo.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.api.Address;
import com.example.administrator.demo.api.ApiKeys;
import com.example.administrator.demo.network.RetrofitRequest;
import com.example.administrator.demo.network.result.WeatherResult;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.ToastUtils;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.net.CommonResponseBean;
import com.example.baselibrary.zh.net.JsonUtils;
import com.example.baselibrary.zh.utils.ActivityUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改手机号
 */
public class UpdatePhoneActivity extends BaseActivity {
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
        return R.layout.activity_phone_update;
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


    @OnClick({R.id.et_number, R.id.et_code, R.id.tv_code, R.id.tv_save})
    public void onClick(View view) {
        String phone = etNumber.getText().toString();
        String code = etCode.getText().toString();
        switch (view.getId()) {
            case R.id.tv_code:
                if (TextUtils.isEmpty(phone)) {
                    showToast(R.string.login_phone_error_hint);
                } else {
                    // TODO: 2019/8/21 调用接口


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
                    // TODO: 2019/8/21 调用接口跳转
                    updatePhone(mContext, phone, code);


                }
                break;
        }
    }

    public void updatePhone(Context context, String userPhone, String userCodeBea) {

        paramMap = new ArrayMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("userPhone", userPhone);
        paramMap.put("smsCount", userCodeBea);
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.update_number_zh, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                Log.d("zhh", weatherResult.getCode() + "==code");
                if (weatherResult.getCode() == 1) {
                    ActivityUtils.startActivity(mContext, UpdatePhoneSuccessActivity.class);
                }

            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });
    }

}
