package com.example.administrator.demo.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.example.baselibrary.zh.utils.AppUtils;
import com.shehuan.nicedialog.BaseNiceDialog;
import com.shehuan.nicedialog.NiceDialog;
import com.shehuan.nicedialog.ViewConvertListener;
import com.shehuan.nicedialog.ViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.rl_number_and)
    RelativeLayout rlNumberAnd;
    @BindView(R.id.rl_read)
    RelativeLayout rlRead;
    @BindView(R.id.rl_cjian)
    RelativeLayout rlCjian;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.rl_clear)
    RelativeLayout rlClear;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.rl_check)
    RelativeLayout rlCheck;
    @BindView(R.id.rl_login_out)
    RelativeLayout rlLoginOut;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.setting), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDate() {
        tvSize.setText(" V" + AppUtils.getAppVersionName());
    }

    @OnClick({R.id.rl_number_and, R.id.rl_read, R.id.rl_cjian, R.id.rl_clear, R.id.rl_check, R.id.rl_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_number_and:
                ActivityUtils.startActivity(mContext, AccountActivity.class);
                break;
            case R.id.rl_read:
                break;
            case R.id.rl_cjian:
                break;
            case R.id.rl_clear:
                NiceDialog.init()
                        .setLayoutId(R.layout.dialog_clear_show)     //设置dialog布局文件
                        .setConvertListener(new ViewConvertListener() {
                            @Override
                            protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {// TODO: 2019/8/21 清除缓存 
                                        dialog.dismiss();
//                                        SPUtils.cleanUserInfo(mContext);
//                                        AppActivityUtils.StartLoginTaskActivity(mContext);
                                    }
                                });
                                holder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        })
                        .setMargin(60)
                        .show(getSupportFragmentManager());

                break;
            case R.id.rl_check:
                break;
            case R.id.rl_login_out:
                NiceDialog.init()
                        .setLayoutId(R.layout.dialog_login_out)     //设置dialog布局文件
                        .setConvertListener(new ViewConvertListener() {
                            @Override
                            protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {// TODO: 2019/8/21 打开登陆页面 
                                        dialog.dismiss();
//                                        SPUtils.cleanUserInfo(mContext);
//                                        AppActivityUtils.StartLoginTaskActivity(mContext);
                                    }
                                });
                                holder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        })
                        .setMargin(60)
                        .show(getSupportFragmentManager());
                break;
        }
    }
}
