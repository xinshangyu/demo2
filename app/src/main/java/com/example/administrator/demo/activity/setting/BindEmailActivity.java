package com.example.administrator.demo.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.utils.SmsTimeUtils;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

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
    private static ConfirmDialog sDialog;

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

        if (SmsTimeUtils.check(SmsTimeUtils.SETTING_FINANCE_ACCOUNT_TIME2, true)) {
            SmsTimeUtils.startCountdown(tvCode, mContext);

            sDialog.newInstance("")
                    .setMargin(60)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());

            Observable.interval(0, 1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (aLong == 2) {
                                sDialog.dismiss();
                            }
                        }
                    });
        } else {
            tvCode.setText(getResources().getString(R.string.privacy_code));
        }
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
                    // TODO: 2019/8/21 调用接口返回成功后显示下面内容，目前没接口
                    showToast(R.string.login_phone_code_success);
                    SmsTimeUtils.check(SmsTimeUtils.SETTING_FINANCE_ACCOUNT_TIME2, false);
                    SmsTimeUtils.startCountdown(tvCode, mContext);
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
                }else {
                    showToast(""+weatherResult.getMsg());
                }

            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });
    }

    public static class ConfirmDialog extends BaseNiceDialog {
        private String type;

        public static ConfirmDialog newInstance(String type) {
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            sDialog = new ConfirmDialog();
            sDialog.setArguments(bundle);
            return sDialog;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle bundle = getArguments();
            if (bundle == null) {
                return;
            }
            type = bundle.getString("type");
        }

        @Override
        public int intLayoutId() {
            return R.layout.dialog_content;
        }

        @Override
        public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
        }
    }
}
