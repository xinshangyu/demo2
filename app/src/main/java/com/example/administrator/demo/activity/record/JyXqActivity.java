package com.example.administrator.demo.activity.record;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 问题帮助
 */
public class JyXqActivity extends BaseActivity {

    @BindView(R.id.tv_rzje)
    TextView tvRzje;
    @BindView(R.id.tv_zffs)
    TextView tvZffs;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_jydh)
    TextView tvJydh;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String content5;

    @Override
    protected int getLayout() {
        return R.layout.item_message_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        content1 = getIntent().getStringExtra("CONTENT1");
        content2 = getIntent().getStringExtra("CONTENT2");
        content3 = getIntent().getStringExtra("CONTENT3");
        content4 = getIntent().getStringExtra("CONTENT4");
        content5 = getIntent().getStringExtra("CONTENT5");

        setTitleBar(getResources().getString(R.string.jyxq), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvRzje.setText(content1);
        tvZffs.setText(content2);
        tvTime.setText(content3);
        tvJydh.setText(content4);
        tvMoney.setText(content5);

    }

    @Override
    protected void initDate() {

    }

}
