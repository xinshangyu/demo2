package com.example.administrator.demo.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.Help2Bean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 问题帮助
 */
public class MoreActivity extends BaseActivity implements CommonView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    private String content;
    private Map<String, String> paramMap;
    private String id;

    @Override
    protected int getLayout() {
        return R.layout.activity_call2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        content = getIntent().getStringExtra("CONTENT");

        setTitleBar(getResources().getString(R.string.help_me), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initDate() {
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData2(getApplicationContext(), cMap, Address.findFAQs);
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        Help2Bean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), Help2Bean.class);
        if (sqBean != null && sqBean.getFaqRecord().size() > 0) {
            id = sqBean.getFaqRecord().get(Integer.parseInt(content)).getId();
            tvName.setText(sqBean.getFaqRecord().get(Integer.parseInt(content)).getPreblemTitle());
            tvContent.setText(sqBean.getFaqRecord().get(Integer.parseInt(content)).getProblemAnswers());
        }
    }

    @Override
    public void onError() {
        showToast("请求失败");
    }


    @OnClick({R.id.tv1, R.id.tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                updatePro(mContext, 1);
                break;
            case R.id.tv2:
                updatePro(mContext, 0);
                break;
        }
    }

    private void updatePro(Context mContext, int i) {
        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        paramMap.put("problemId", id);
        paramMap.put("isSovled", "" + i);
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.problemFeedback, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(getBaseContext()) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                Log.d("zhh", weatherResult.getCode() + "==code");
                if (weatherResult.getCode() == 200) {
                    showToast("感谢您的反馈，我们会继续努力的");
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
