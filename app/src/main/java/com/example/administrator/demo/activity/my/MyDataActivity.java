package com.example.administrator.demo.activity.my;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.MyDataAdapter;
import com.example.administrator.demo.entity.MyDataBean;
import com.example.administrator.demo.weight.GlideImageLoader;
import com.example.baselibrary.zh.base.BaseActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class MyDataActivity extends BaseActivity {
    @BindView(R.id.home_search_edittext)
    EditText homeSearchEdittext;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.navigation_view)
    BottomNavigationView navigationView;
    @BindView(R.id.my)
    TextView my;

    private List<MyDataBean> mBeanList = new ArrayList<>();
    private MyDataBean bean;
    private MyDataAdapter mAdapter;
    private List<Integer> images = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_mydata;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        images.add(R.drawable.icon);
        images.add(R.drawable.icon);
        images.add(R.drawable.icon);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        GridLayoutManager manager = new GridLayoutManager(MyDataActivity.this,3);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MyDataAdapter(mBeanList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyDataBean bean = (MyDataBean) adapter.getItem(position);
                if("-1".equals(bean.getId())){
                    new MaterialFilePicker()
                            .withActivity(MyDataActivity.this)
                            .withRequestCode(1)
                            .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                            .withFilterDirectories(true) // Set directories filterable (false by default)
                            .withHiddenFiles(true) // Show hidden files and folders
                            .start();

                }
            }
        });

    }

    @Override
    protected void initDate() {

        for(int i = 0; i < 10; i++){
            bean = new MyDataBean();
            bean.setName("书名" + i);
            bean.setId("0");
            mBeanList.add(bean);
        }
        bean = new MyDataBean();
        bean.setName("");
        bean.setId("-1");
        mBeanList.add(bean);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.terrace, R.id.my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.terrace:
                //平台资料是

                break;
            case R.id.my:
                //我的资料

                break;
        }
    }
}
