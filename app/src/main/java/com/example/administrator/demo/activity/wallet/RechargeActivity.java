package com.example.administrator.demo.activity.wallet;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.RechargeAdapter;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.net.CommonResponseBean;
import com.example.baselibrary.zh.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件名：充值
 */
public class RechargeActivity extends BaseActivity {
    @BindView(R.id.recyclerView_pay)
    RecyclerView mRecyclerViewPay;

    RechargeAdapter adapter;

    @BindView(R.id.CheckBox_zfb)
    CheckBox mCheckBoxZfb;
    @BindView(R.id.CheckBox_weChat)
    CheckBox mCheckBoxWeChat;
    @BindView(R.id.tv_start_Rent)
    TextView mTvStartRent;

    private List<String> mList = new ArrayList<>();
    private List<String> mListData = new ArrayList<>();

    private int pay_type = 1;//默认微信

    @Override
    protected int getLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.recharge), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initDate() {
        mListData.add("1");
        mListData.add("10");
        mListData.add("50");
        mListData.add("100");
        mListData.add("1000");

        for (int i = 0; i < 5; i++) {
            mList.add(mListData.get(i));
        }
        mRecyclerViewPay.setLayoutManager(new GridLayoutManager(mContext, 3));
        adapter = new RechargeAdapter(mContext, mList);
        mRecyclerViewPay.setAdapter(adapter);
    }

    @OnClick({R.id.CheckBox_zfb, R.id.CheckBox_weChat, R.id.tv_start_Rent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.CheckBox_zfb:
                mCheckBoxZfb.setChecked(true);
                mCheckBoxWeChat.setChecked(!mCheckBoxZfb.isChecked());
                pay_type = 1;
                break;
            case R.id.CheckBox_weChat:
                mCheckBoxWeChat.setChecked(true);
                mCheckBoxZfb.setChecked(!mCheckBoxWeChat.isChecked());
                pay_type = 2;
                break;
            case R.id.tv_start_Rent:
                int selectPosition = adapter.getSelectPosition();
                showToast("============" + selectPosition);
                break;
        }
    }
}
