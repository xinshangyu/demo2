package com.example.administrator.demo.activity.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.PagerAdapter;
import com.example.administrator.demo.entity.TabBean;
import com.example.administrator.demo.fragment.HositoryFragment;
import com.example.administrator.demo.fragment.My_ReadFragment;
import com.example.administrator.demo.weight.NoPreloadViewPager;
import com.example.baselibrary.zh.base.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;

public class TheInfoActivity extends BaseActivity implements OnTabSelectListener, NoPreloadViewPager.OnPageChangeListener {

    @BindView(R.id.home_tabLayout)
    CommonTabLayout mTabLayout;@BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.home_NoScrollViewPager)
    NoPreloadViewPager mViewPager;

    @BindString(R.string.my_see)
    String home;
    @BindString(R.string.lscj)
    String mine;
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String content;

    @Override
    protected int getLayout() {
        return R.layout.activity_the_info_head;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        content = getIntent().getStringExtra("CONTENT");

        setTitleBar("他的资料", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTitles = new String[]{home, mine};
        mFragments.add(My_ReadFragment.newInstance("", ""));
        mFragments.add(HositoryFragment.newInstance("", ""));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabBean(mTitles[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setCurrentTab(0);
        mTabLayout.setOnTabSelectListener(this);
        mViewPager.setAdapter(new PagerAdapter(this.getSupportFragmentManager(), mTitles, mFragments));

        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(2);

    }

    @Override
    protected void initDate() {
        tv_name.setText(content);
    }

    @Override
    public void onTabSelect(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {
        mTabLayout.setCurrentTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
