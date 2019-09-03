package com.example.administrator.demo.activity.comment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.PagerAdapter;
import com.example.administrator.demo.entity.QuickReturnTopEvent;
import com.example.administrator.demo.entity.TabBean;
import com.example.administrator.demo.fragment.CommentFragment;
import com.example.administrator.demo.fragment.LiFragment;
import com.example.administrator.demo.fragment.ScFragment;
import com.example.administrator.demo.fragment.ZanFragment;
import com.example.baselibrary.zh.base.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 收藏，获赞，历史，评论
 */
public class Sc_Zan_Comment_LsActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.home_search_edittext)
    EditText homeSearchEdittext;
    @BindView(R.id.home_search)
    public TextView homeSearch;
    @BindView(R.id.home_tabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.home_NoScrollViewPager)
    ViewPager mViewPager;

    @BindString(R.string.scang)
    String scang;
    @BindString(R.string.pl)
    String pl;
    @BindString(R.string.zan)
    String zan;
    @BindString(R.string.ls)
    String ls;

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
        tabNum = i;
        Log.d("zhh", "onPageSelected: " + i);
//        EventBus.getDefault().post(new QuickReturnTopEvent("SC2"));
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_four;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        //接受状态值
        String string = getIntent().getStringExtra("tabNum");
        tabNum = Integer.parseInt(string);


        mTitles = new String[]{scang, pl, zan, ls};
        mFragments.add(ScFragment.newInstance(""));
        mFragments.add(CommentFragment.newInstance("", ""));
        mFragments.add(ZanFragment.newInstance("", ""));
        mFragments.add(LiFragment.newInstance("", ""));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabBean(mTitles[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        if (tabNum == 0) {
            mTabLayout.setCurrentTab(0);
        } else if (tabNum == 1) {
            mTabLayout.setCurrentTab(1);
        } else if (tabNum == 2) {
            mTabLayout.setCurrentTab(2);
        } else if (tabNum == 3) {
            mTabLayout.setCurrentTab(3);
        }
        mTabLayout.setOnTabSelectListener(this);
        mViewPager.setAdapter(new PagerAdapter(this.getSupportFragmentManager(), mTitles, mFragments));
        mViewPager.setCurrentItem(tabNum);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(5);
    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onTabSelect(int position) {
        mViewPager.setCurrentItem(position);
        tabNum = position;
        Log.d("zhh", "onTabSelect: " + position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @OnClick({R.id.iv_back, R.id.home_search_edittext,  R.id.home_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.home_search_edittext:
                break;
            case R.id.home_search:
                if (tabNum == 0) {
                    EventBus.getDefault().post(new QuickReturnTopEvent("SC"));
                } else if (tabNum == 1) {

                } else if (tabNum == 2) {

                } else if (tabNum == 3) {

                }
                break;
        }
    }
}
