package com.example.administrator.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.money.MyMoneyActivity;
import com.example.administrator.demo.base.BaseActivity;
import com.example.administrator.demo.mvp.iview.MyModularView;
import com.example.administrator.demo.mvp.presenter.MyModularPresenter;
import com.example.administrator.demo.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/***
 * 我的模块
 */
public class MyModularActivity extends BaseActivity implements MyModularView {
    //昵称
    @BindView(R.id.iv_personal_user_name)
    TextView tvUserName;
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

    private MyModularPresenter myModularPresenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_my_modular;
    }

    @Override
    public void initView() {
        myModularPresenter = new MyModularPresenter(this);
        myModularPresenter.requestRanking(this);
    }

    /***
     * 返回
     */

    @OnClick(R.id.iv_run)
    void onRun(){
        finish();
    }

    /**
     * 分享
     **/
    @OnClick(R.id.iv_personal_user_open)
    void onOpen() {


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

    }

    /**
     * 获赞
     **/
    @OnClick(R.id.ll_personal_user_identify)
    void onIdentify() {


    }

    /**
     * 我的收藏
     **/
    @OnClick(R.id.ll_personal_operation1)
    void onclickCollection() {

    }

    /**
     * 浏览历史
     **/
    @OnClick(R.id.ll_personal_operation2)
    void onclickAfter(View view) {

    }


    /**
     * 我的评论
     **/
    @OnClick(R.id.ll_personal_operation3)
    void onclickComment() {

    }


    /**
     * 我的点赞
     **/
    @OnClick(R.id.ll_personal_operation4)
    void onclickIdentify() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_dialog_be_praised, null);
        localBuilder.setView(view);
        final AlertDialog dia = localBuilder.show();
        TextView understand = view.findViewById(R.id.tv_praised_understand);
        understand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
    }

    /**
     * 钱包
     **/
    @OnClick(R.id.onclickWallet)
    void onclickWallet() {
        ActivityUtils.startActivity(this, MyMoneyActivity.class);
    }

    /**
     * 我的VIP
     **/
    @OnClick(R.id.onclickVIP)
    void onclickVIP() {

    }

    /**
     * 足迹
     **/
    @OnClick(R.id.onclickFootprint)
    void onclickFootprint() {

    }


    /**
     * 我的组织
     **/
    @OnClick(R.id.onclickOrganization)
    void onclickOrganization() {

    }


    /**
     * 积分商城
     **/
    @OnClick(R.id.onclickIntegral)
    void onclickIntegral() {

    }


    /**
     * 消息通知
     **/
    @OnClick(R.id.onclickNews)
    public void onclickNews() {

    }


    /**
     * 帮助中心
     **/
    @OnClick(R.id.onclickHelp)
    void onclickHelp() {

    }


    /**
     * 设置
     **/
    @OnClick(R.id.onclickSet)
    void onclickSet() {

    }

    @Override
    public void onMyModular(WeatherResult weatherResult) {

    }
}
