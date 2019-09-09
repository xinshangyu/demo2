package com.example.administrator.demo.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.UpdateUserInfoBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑个人中心
 */
public class UpdateMyInfoActivity extends BaseActivity implements CommonView {

    @BindView(R.id.iv_my_head)
    ImageView ivMyHead;
    @BindView(R.id.rl_my_headImage)
    RelativeLayout rlMyHeadImage;
    @BindView(R.id.tv_nick)
    EditText tvNick;
    @BindView(R.id.et_nick)
    EditText et_nick;
    @BindView(R.id.rl_tv_nick_update)
    RelativeLayout rlTvNickUpdate;
    @BindView(R.id.rl_tv_nick)
    RelativeLayout rlTvNick;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_xl)
    TextView tvXl;
    @BindView(R.id.rl_xl)
    RelativeLayout rlXl;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_gsmc)
    TextView tvGsmc;
    @BindView(R.id.tv_bm)
    TextView tvBm;
    @BindView(R.id.tv_byyx)
    TextView tvByyx;
    @BindView(R.id.tv_zy)
    TextView tvZy;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.mydata), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {
        cMap.put("userId", "" + SharedPreferencesHelper.getPrefString("userId", ""));
        cPresenter.requestData2(getApplicationContext(), cMap, Address.edit);
    }


    @OnClick({R.id.iv_my_head, R.id.rl_my_headImage, R.id.rl_sex, R.id.rl_xl, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_head:
                break;
            case R.id.rl_my_headImage:
                break;
            case R.id.rl_sex:
                cMap.put("userId", "" + SharedPreferencesHelper.getPrefString("userId", ""));
                cPresenter.requestData2(getApplicationContext(), cMap, Address.userSex);

                break;
            case R.id.rl_xl:
                cMap.put("userId", "" + SharedPreferencesHelper.getPrefString("userId", ""));
                cPresenter.requestData2(getApplicationContext(), cMap, Address.educationBackground);

                break;
            case R.id.tv_save:
                break;
        }
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        UpdateUserInfoBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), UpdateUserInfoBean.class);
        if (sqBean != null && sqBean.getUserInfo() != null) {
            tvNick.setText(""+sqBean.getUserInfo().getUserName());
            et_nick.setText(""+sqBean.getUserInfo().getUserCode().getNickName());
            tvGsmc.setText(""+sqBean.getUserInfo().getOrgInfo().getCompanyName());
            tvBm.setText(""+sqBean.getUserInfo().getOrgInfo().getDeptName());
            tvZy.setText(""+sqBean.getUserInfo().getProfession());
            tvAddress.setText(""+sqBean.getUserInfo().getHomeSite());
        }
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
