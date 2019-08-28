package com.example.administrator.demo.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.base.BaseActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDataActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.home_search)
    ImageView homeSearch;
    @BindView(R.id.home_search_edittext)
    EditText homeSearchEdittext;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.navigation_view)
    BottomNavigationView navigationView;
    @BindView(R.id.terrace)
    TextView terrace;
    @BindView(R.id.my)
    TextView my;


    @Override
    public int intiLayout() {
        return R.layout.activity_mydata;
    }

    @Override
    public void initView() {

        GridLayoutManager manager = new GridLayoutManager(MyDataActivity.this,3);
        recyclerView.setLayoutManager(manager);
        MyDataAdapter adapter = new MyDataAdapter(MyDataActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.setOncItemClike(new MyDataAdapter.Onclike() {
            @Override
            public void selectclike(int i) {

            }

            @Override
            public void imageclike(int i) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @OnClick({R.id.terrace, R.id.my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.terrace:
                //平台资料

                break;
            case R.id.my:
                //我的资料

                break;
        }
    }
}
