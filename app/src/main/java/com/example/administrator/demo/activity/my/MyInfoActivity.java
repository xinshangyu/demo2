package com.example.administrator.demo.activity.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.personal.MyPersonalSQActivity;
import com.example.administrator.demo.activity.user.UserFollowActivity;
import com.example.administrator.demo.adapter.PagerAdapter;
import com.example.administrator.demo.entity.MyModularBen;
import com.example.administrator.demo.entity.TabBean;
import com.example.administrator.demo.fragment.HositoryFragment;
import com.example.administrator.demo.fragment.My_ReadFragment;
import com.example.administrator.demo.utils.SPUtils;
import com.example.administrator.demo.weight.NoPreloadViewPager;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.example.baselibrary.zh.utils.ImageLoader;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.example.administrator.demo.base.BaseActivity.replaceNULL;

/**
 * 个人中心
 */
public class MyInfoActivity extends BaseActivity implements OnTabSelectListener, NoPreloadViewPager.OnPageChangeListener {

    @BindView(R.id.home_tabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.home_NoScrollViewPager)
    NoPreloadViewPager mViewPager;
    @BindView(R.id.iv_imageView)
    ImageView iv_imageView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_da)
    TextView tv_da;
    @BindView(R.id.tv_date2)
    TextView tv_date2;
    //关注个数
    @BindView(R.id.tv_personal_user_follow)
    TextView tvUserFollw;
    //粉丝个数
    @BindView(R.id.tv_personal_user_fans)
    TextView tvUserFans;
    //商圈个数
    @BindView(R.id.tv_personal_user_circle)
    TextView tvUserCircle;
    //获赞个数
    @BindView(R.id.tv_personal_user_identify)
    TextView tvUserIdentify;

    @BindString(R.string.my_see)
    String home;
    @BindString(R.string.lscj)
    String mine;
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyModularBen.DataBean.UserInfoBean mUserInfo;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_info_head;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mUserInfo = SPUtils.getUserInfo(getApplicationContext());
        if (mUserInfo != null) {
            String userPhoto = mUserInfo.getUserPhoto();
            ImageLoader.getInstance().loadingImage(getApplicationContext(), ApiKeys.getApiUrl() + Address.fileId + userPhoto, iv_imageView,
                    new MultiTransformation(new CircleCrop()), R.drawable.defaulthead);
            tv_name.setText("" + mUserInfo.getPetName());
            tv_date.setText("" + mUserInfo.getProvinceId());
            tv_da.setText("" + mUserInfo.getCityId());
            tv_date2.setText("" + mUserInfo.getUserSignature());

            tvUserCircle.setText(replaceNULL(mUserInfo.getCircleNumber() + ""));
            tvUserFollw.setText(replaceNULL(mUserInfo.getAttentionNumber() + ""));
            tvUserIdentify.setText(replaceNULL(mUserInfo.getPraiseNumber() + ""));
            tvUserFans.setText(replaceNULL(mUserInfo.getFansNumber() + ""));
        }
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
        mViewPager.setOffscreenPageLimit(1);
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

    /**
     * 关注
     **/
    @OnClick(R.id.ll_personal_user_follow)
    void onFollow() {
        Bundle bundle = new Bundle();
        bundle.putString("tabNum", "0");
        startActivity(UserFollowActivity.class, bundle);
    }


    /**
     * 粉丝
     **/
    @OnClick(R.id.ll_personal_user_fans)
    void onFans() {
        Bundle bundle = new Bundle();
        bundle.putString("tabNum", "1");
        startActivity(UserFollowActivity.class, bundle);
    }

    /**
     * 商圈
     **/
    @OnClick(R.id.ll_personal_user_circle)
    void onCircle() {
        ActivityUtils.startActivity(this, MyPersonalSQActivity.class);
    }

    /**
     * 获赞
     **/
    @OnClick(R.id.ll_personal_user_identify)
    void onIdentify() {
        NiceDialog.init()
                .setLayoutId(R.layout.alert_dialog_be_praised)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder viewHolder, final BaseNiceDialog dialog) {
                        TextView view = viewHolder.getView(R.id.tv_name);
                        TextView tv = viewHolder.getView(R.id.tv);
                        tv.setText("" + mUserInfo.getPraiseNumber());
                        TextView tv_praised_understand = viewHolder.getView(R.id.tv_praised_understand);
                        view.setText(mUserInfo.getPetName());
                        tv_praised_understand.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserInfo = SPUtils.getUserInfo(getApplicationContext());
        if (mUserInfo != null) {
            String userPhoto = mUserInfo.getUserPhoto();
            ImageLoader.getInstance().loadingImage(getApplicationContext(), ApiKeys.getApiUrl() + Address.fileId + userPhoto, iv_imageView,
                    new MultiTransformation(new CircleCrop()), R.drawable.defaulthead);
            tv_name.setText("" + mUserInfo.getPetName());
            tv_date.setText("" + mUserInfo.getProvinceId());
            tv_da.setText("" + mUserInfo.getCityId());
            tv_date2.setText("" + mUserInfo.getUserSignature());

            tvUserCircle.setText(replaceNULL(mUserInfo.getCircleNumber() + ""));
            tvUserFollw.setText(replaceNULL(mUserInfo.getAttentionNumber() + ""));
            tvUserIdentify.setText(replaceNULL(mUserInfo.getPraiseNumber() + ""));
            tvUserFans.setText(replaceNULL(mUserInfo.getFansNumber() + ""));
        }
    }

    @OnClick({R.id.iv_run, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_run:
                finish();
                break;
            case R.id.tv_save:
                ActivityUtils.startActivity(mContext, UpdateMyInfoActivity.class);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
