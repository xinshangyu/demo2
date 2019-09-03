package com.example.administrator.demo.activity.my;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.adress.ZjActivity;
import com.example.administrator.demo.activity.comment.Sc_Zan_Comment_LsActivity;
import com.example.administrator.demo.activity.money.MyMoneyActivity;
import com.example.administrator.demo.activity.personal.MyPersonalSQActivity;
import com.example.administrator.demo.activity.record.IntegralShappingActivity;
import com.example.administrator.demo.activity.setting.HelpActivity;
import com.example.administrator.demo.activity.setting.SettingActivity;
import com.example.administrator.demo.activity.user.UserFollowActivity;
import com.example.administrator.demo.activity.vip.MyVipActivity;
import com.example.administrator.demo.activity.wallet.RechargeActivity;
import com.example.administrator.demo.base.BaseActivity;
import com.example.administrator.demo.entity.MyModularBen;
import com.example.administrator.demo.mvp.iview.MyModularView;
import com.example.administrator.demo.mvp.presenter.MyModularPresenter;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.example.baselibrary.zh.utils.ToastUtils;
import com.google.gson.Gson;

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

    @BindView(R.id.iv_my_head)
    ImageView iv_my_head;


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
    void onRun() {
        finish();
    }

    /***
     * 个人中心
     */

    @OnClick(R.id.iv_my_head)
    void onHead() {
        ActivityUtils.startActivity(this, MyInfoActivity.class);
    }

    /**
     * 分享
     **/
    @OnClick(R.id.iv_personal_user_open)
    void onOpen() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_share)     //设置dialog布局文件
                .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
                    @Override
                    public void convertView(ViewHolder viewHolder, final BaseNiceDialog dialog) {
                        viewHolder.setOnClickListener(R.id.ll_sq, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                ToastUtils.showToast(getApplicationContext(), "sq");
                            }
                        });
                        viewHolder.setOnClickListener(R.id.ll_wx, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                ToastUtils.showToast(getApplicationContext(), "wx");
                            }
                        });
                        viewHolder.setOnClickListener(R.id.ll_qq, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                ToastUtils.showToast(getApplicationContext(), "qq");
                            }
                        });
                        viewHolder.setOnClickListener(R.id.tv_save, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        viewHolder.setOnClickListener(R.id.rl_image, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setGravity(Gravity.BOTTOM)
                .show(getSupportFragmentManager());
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
     * 用户名
     **/
    @OnClick(R.id.iv_personal_user_name)
    void onName() {

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


    }

    /**
     * 我的收藏
     **/
    @OnClick(R.id.ll_personal_operation1)
    void onclickCollection() {
        Bundle bundle = new Bundle();
        bundle.putString("tabNum", "0");
        startActivity(Sc_Zan_Comment_LsActivity.class, bundle);
    }

    /**
     * 浏览历史
     **/
    @OnClick(R.id.ll_personal_operation2)
    void onclickAfter(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("tabNum", "3");
        startActivity(Sc_Zan_Comment_LsActivity.class, bundle);
    }


    /**
     * 我的评论
     **/
    @OnClick(R.id.ll_personal_operation3)
    void onclickComment() {
        Bundle bundle = new Bundle();
        bundle.putString("tabNum", "1");
        startActivity(Sc_Zan_Comment_LsActivity.class, bundle);
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
     * 充值
     **/
    @OnClick(R.id.tv_cz)
    void onclickCz() {
        ActivityUtils.startActivity(this, RechargeActivity.class);
    }

    /**
     * 我的VIP
     **/
    @OnClick(R.id.onclickVIP)
    void onclickVIP() {
        ActivityUtils.startActivity(this, MyVipActivity.class);
    }

    /**
     * 足迹
     **/
    @OnClick(R.id.onclickFootprint)
    void onclickFootprint() {
        ActivityUtils.startActivity(this, ZjActivity.class);

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
        ActivityUtils.startActivity(this, IntegralShappingActivity.class);
    }


    /**
     * 消息通知
     **/
    @OnClick(R.id.onclickNews)
    public void onclickNews() {
        ToastUtils.showToast(this, "不用写...");
    }


    /**
     * 帮助中心
     **/
    @OnClick(R.id.onclickHelp)
    void onclickHelp() {
        ActivityUtils.startActivity(this, HelpActivity.class);
    }


    /**
     * 设置
     **/
    @OnClick(R.id.onclickSet)
    void onclickSet() {
        ActivityUtils.startActivity(this, SettingActivity.class);
    }

    @Override
    public void onMyModular(WeatherResult weatherResult) {
        MyModularBen myModularBen;
        myModularBen = new Gson().fromJson(new Gson().toJson(weatherResult), MyModularBen.class);
        if (myModularBen == null || myModularBen.getData() == null || myModularBen.getData().getUserInfo() == null) {
            return;
        }
        tvUserName.setText(replaceNULL(myModularBen.getData().getUserInfo().getPetName()));
        tvUserCircle.setText(replaceNULL(myModularBen.getData().getUserInfo().getCircleNumber() + ""));
        tvUserFollw.setText(replaceNULL(myModularBen.getData().getUserInfo().getAttentionNumber() + ""));
        tvUserIdentify.setText(replaceNULL(myModularBen.getData().getUserInfo().getPraiseNumber() + ""));
        tvUserFans.setText(replaceNULL(myModularBen.getData().getUserInfo().getFansNumber() + ""));
    }
}
