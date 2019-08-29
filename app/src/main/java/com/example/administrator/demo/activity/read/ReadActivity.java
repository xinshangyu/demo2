package com.example.administrator.demo.activity.read;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.Read1Adapter;
import com.example.administrator.demo.adapter.Read2Adapter;
import com.example.baselibrary.zh.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 阅读偏好
 */
public class ReadActivity extends BaseActivity {

    @BindView(R.id.RecyclerView)
    RecyclerView RecyclerView;
    @BindView(R.id.RecyclerView2)
    RecyclerView RecyclerView2;

    private ArrayList<String> mListData = new ArrayList<>();
    private Read1Adapter adapter;
    private Read2Adapter adapter2;

    @Override
    protected int getLayout() {
        return R.layout.activity_read;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.zhhy), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initDate() {

        for (int i = 0; i < 5; i++) {
            mListData.add("的的" + i);
        }

        RecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new Read1Adapter(mContext, mListData);
        RecyclerView.setAdapter(adapter);

        RecyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
        adapter2 = new Read2Adapter(mContext, mListData);
        RecyclerView2.setAdapter(adapter2);

    }
}
