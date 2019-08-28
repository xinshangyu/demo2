package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.HelpBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 客服中心
 */
public class HelpActivity extends BaseActivity implements CommonView {

    @BindView(R.id.common_toolBar_image_right)
    ImageButton commonToolBarImageRight;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_content2)
    TextView tvContent2;
    @BindView(R.id.tv_content3)
    TextView tvContent3;

    @Override
    protected int getLayout() {
        return R.layout.activity_call;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.help), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, true, getResources().getString(R.string.feedback));
    }

    @Override
    protected void initDate() {

        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestDataKF(getApplicationContext(), cMap, Address.findServiceInfo);

//        cPresenter.requestDataFAQ(getApplicationContext(), cMap, Address.findFAQs);


    }

    @OnClick(R.id.common_toolBar_text_right)
    public void onClick(View view) {
        if (view.getId() == R.id.common_toolBar_text_right) {
            ActivityUtils.startActivity(mContext, CallBackActivity.class);
        }

    }

    @Override
    public void onData(WeatherResult weatherResult) {
            HelpBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), HelpBean.class);
            if (sqBean != null) {
                tvContent.setText("" + sqBean.getSysInfo().getOfficalAccounts());
                tvContent2.setText("" + sqBean.getSysInfo().getServiceTel());
                tvContent3.setText("" + sqBean.getSysInfo().getServiceTime());
            }
    }

}
