package com.example.administrator.demo.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.PagerAdapter;
import com.example.administrator.demo.entity.TabBean;
import com.example.administrator.demo.fragment.FollowFragment;
import com.example.administrator.demo.fragment.UnFollowFragment;
import com.example.baselibrary.zh.base.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关注/粉丝
 **/
public class UserFollowActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.home_tabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.home_NoScrollViewPager)
    ViewPager mViewPager;
    @BindString(R.string.follow)
    String home;
    @BindString(R.string.fs)
    String mine;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private int tabNum;

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
    protected int getLayout() {
        return R.layout.activity_user_follow;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //接受状态值
        String string = getIntent().getStringExtra("tabNum");
        tabNum = Integer.parseInt(string);


        mTitles = new String[]{home, mine};
        mFragments.add(FollowFragment.newInstance("", ""));
        mFragments.add(UnFollowFragment.newInstance("", ""));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabBean(mTitles[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        if (tabNum == 0) {
            mTabLayout.setCurrentTab(0);
        } else if (tabNum == 1) {
            mTabLayout.setCurrentTab(1);
        }
        mTabLayout.setOnTabSelectListener(this);
        mViewPager.setAdapter(new PagerAdapter(this.getSupportFragmentManager(), mTitles, mFragments));
        mViewPager.setCurrentItem(tabNum);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(2);

    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onTabSelect(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
//    String[] layoutTables = {"关注", "粉丝"};
//    @BindView(R.id.tl_persona_details)
//    TabLayout tabLayout;
//    @BindView(R.id.title_left_iv)
//    ImageView run;
//    @BindView(R.id.title_name_tv)
//    TextView title;
//    @BindView(R.id.lv_user_follow)
//    ListView listView;
//    UserFollowAdapter userFollowAdapter;
//    private int tabNum;
//
//    @Override
//    public int intiLayout() {
//        return R.layout.activity_user_follow;
//    }
//
//    @Override
//    public void initView() {
//        run.setVisibility(View.VISIBLE);
//        title.setText("@");
//        //初始化TabLayout
//        initTabLayout();
//        //接受状态值
//        String string = getIntent().getStringExtra("tabNum");
//        tabNum = Integer.parseInt(string);
//        setState();
//    }
//
//
//    public List<UserFollowBen> getData() {
//        List<UserFollowBen> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            UserFollowBen userFollowBen = new UserFollowBen();
//            userFollowBen.setName("昵称");
//            userFollowBen.setPic("https://cdn.duitang.com/uploads/item/201601/08/20160108194244_JxGRy.thumb.700_0.jpeg");
//            userFollowBen.setQianming("今天是个好天气");
//            userFollowBen.setTag("已关注");
//            list.add(userFollowBen);
//        }
//
//        return list;
//    }
//
//    public List<UserFollowBen> getData2() {
//        List<UserFollowBen> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            UserFollowBen userFollowBen = new UserFollowBen();
//            userFollowBen.setName("昵称");
//            userFollowBen.setPic("https://cdn.duitang.com/uploads/item/201601/08/20160108194244_JxGRy.thumb.700_0.jpeg");
//            userFollowBen.setQianming("今天是个好天气");
//            userFollowBen.setTag("关注");
//            list.add(userFollowBen);
//        }
//
//        return list;
//    }
//
//    private void initTabLayout() {
//        tabLayout.setTabMode(TabLayout.FOCUSABLE);
//        for (int i = 0; i < layoutTables.length; i++) {
//            // 设置条目 tabItem
//            tabLayout.addTab(tabLayout.newTab().setText(layoutTables[i]));
//        }
//        tabLayout.addOnTabSelectedListener(this);
//    }
//
//    @OnClick(R.id.ll_left)
//    void onRun() {
//        finish();
//
//    }
//
//    //TODO:tab选择
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        tabNum = tab.getPosition();
//        setState();
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }
//
//
//    /**
//     * 状态统一方法
//     **/
//
//    public void setState() {
//
//        switch (tabNum) {
//            case 1://粉丝
//                tabLayout.getTabAt(tabNum).select();
//                userFollowAdapter = new UserFollowAdapter(getData2(), this, R.layout.adapter_user_follow);
//                listView.setAdapter(userFollowAdapter);
//                userFollowAdapter.notifyDataSetChanged();
//                break;
//
//            case 0://关注
//                tabLayout.getTabAt(tabNum).select();
//                userFollowAdapter = new UserFollowAdapter(getData(), this, R.layout.adapter_user_follow);
//                listView.setAdapter(userFollowAdapter);
//                userFollowAdapter.notifyDataSetChanged();
//                break;
//
//        }
//
//    }
}
