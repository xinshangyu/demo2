package com.example.administrator.demo.activity.money;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.record.RecordActivity;
import com.example.administrator.demo.activity.wallet.RechargeActivity;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
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

    private Map<String, String> paramMap;

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
        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.accountBalance, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(mContext) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                String json = new Gson().toJson(weatherResult);
                LogUtil.e("返回数据" + json);
                if (weatherResult.getCode() == 200) {
                    try {
                        JSONObject object = new JSONObject(json);
                        String currencyNumber = object.optString("currencyNumber");
                        if ("".equals(currencyNumber)) tvMoney.setText("0");
                        else tvMoney.setText("" + currencyNumber);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

    @OnClick({R.id.tv_save, R.id.rl_yhj, R.id.rl_daoju, R.id.rl_jyjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                ActivityUtils.startActivity(mContext, RechargeActivity.class);
                break;
            case R.id.rl_yhj:
                showToast("接口没有...");
                break;
            case R.id.rl_daoju://跳转道具页面
                ActivityUtils.startActivity(mContext, DaoJuActivity.class);
                break;
            case R.id.rl_jyjl:
                ActivityUtils.startActivity(mContext, RecordActivity.class);
                break;
        }
    }
}
