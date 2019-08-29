package com.example.administrator.demo.activity.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.setting.CallBackActivity;
import com.example.administrator.demo.adapter.PagerAdapter;
import com.example.administrator.demo.entity.TabBean;
import com.example.administrator.demo.fragment.FollowFragment;
import com.example.administrator.demo.fragment.HositoryFragment;
import com.example.administrator.demo.fragment.My_ReadFragment;
import com.example.administrator.demo.fragment.UnFollowFragment;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人中心
 */
public class MyInfoActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.home_tabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.home_NoScrollViewPager)
    ViewPager mViewPager;

    @BindString(R.string.my_see)
    String home;
    @BindString(R.string.lscj)
    String mine;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_my_info_head;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar2("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, true, getResources().getString(R.string.update_data));

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

        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initDate() {


    }

    @OnClick(R.id.tv_save)
    public void onClick(View view) {
        if (view.getId() == R.id.tv_save) {
            ActivityUtils.startActivity(mContext, UpdateMyInfoActivity.class);
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mTabLayout.setCurrentTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void onTabSelect(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }
}
