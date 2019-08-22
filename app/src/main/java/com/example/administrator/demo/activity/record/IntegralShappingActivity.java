package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.setting.UpdatePwdActivity;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 积分商城
 */
public class IntegralShappingActivity extends BaseActivity {

    @BindView(R.id.iv_my_head)
    ImageView ivMyHead;
    @BindView(R.id.ll_jf1)
    LinearLayout llJf1;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;
    @BindView(R.id.ll_gz)
    LinearLayout llGz;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected int getLayout() {
        return R.layout.activity_integral;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.jf_sc), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void initDate() {


    }

    @OnClick({R.id.ll_jf1, R.id.ll_detail, R.id.ll_gz, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_jf1:
                break;
            case R.id.ll_detail:
                ActivityUtils.startActivity(mContext, IntegralActivity.class);
                break;
            case R.id.ll_gz:
                break;
            case R.id.tv_save:

                showToast("去商城");
                break;
        }
    }
}
