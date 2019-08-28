package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.Help2Bean;
import com.example.administrator.demo.entity.HelpBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;

import butterknife.BindView;

/**
 * 帮助
 */
public class MoreActivity extends BaseActivity implements CommonView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private String content;

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
        cPresenter.requestDataFAQ(getApplicationContext(), cMap, Address.findFAQs);

    }

    @Override
    public void onData(WeatherResult weatherResult) {
        Help2Bean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), Help2Bean.class);
        if (sqBean != null && sqBean.getFaqRecord().size() > 0) {
            tvName.setText(sqBean.getFaqRecord().get(Integer.parseInt(content) - 1).getPreblemTitle());
            tvContent.setText(sqBean.getFaqRecord().get(Integer.parseInt(content) - 1).getProblemAnswers());
        }
    }
}