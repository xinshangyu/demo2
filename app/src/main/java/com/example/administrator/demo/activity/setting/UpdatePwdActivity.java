package com.example.administrator.demo.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    private Map<String, String> paramMap;

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
                String mEtOldNumber = etOldNumber.getText().toString();
                String mEtNewNumber = etNewNumber.getText().toString();
                String mEetNewNumber2 = etNewNumber2.getText().toString();
                String userPwd = SharedPreferencesHelper.getPrefString("userPwd", "");

                if (TextUtils.isEmpty(mEtOldNumber) && !userPwd.equals(mEtOldNumber)) {
                    showToast(getString(R.string.set_pwd));
                    return;
                } else if (TextUtils.isEmpty(mEtNewNumber)) {
                    showToast(R.string.set_pwd2);
                    return;
                } else if (TextUtils.isEmpty(mEetNewNumber2)) {
                    showToast(R.string.set_pwd3);
                    return;
                } else if (!mEtNewNumber.equals(mEetNewNumber2)) {
                    showToast(R.string.set_pwd4);
                    return;
                } else {
                    updatePWD(mContext, mEtOldNumber, mEtNewNumber);
                }
                break;
        }
    }

    public void updatePWD(Context context, String etOldNumber, String etNewNumber) {

        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("passWord", etOldNumber);
        paramMap.put("newPassWord", etNewNumber);

        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl3() + Address.byOldPwd, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                Log.d("zhh", weatherResult.getCode() + "==code");
                if (weatherResult.getCode() == 200) {
                    ActivityUtils.startActivity(mContext, UpdatePwdSuccessActivity.class);
                }

            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });
    }

}
