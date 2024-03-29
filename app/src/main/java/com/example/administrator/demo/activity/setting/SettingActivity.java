package com.example.administrator.demo.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.activity.read.ReadActivity;
import com.example.administrator.demo.entity.VersionBean;
import com.example.administrator.demo.utils.CacheDataManager;
import com.example.administrator.demo.weight.AppActivityUtils;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
import com.example.baselibrary.NumberFormatUtils;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.example.baselibrary.zh.utils.AppUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity implements CommonView {

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

    private Map<String, String> paramMap;

    private NiceDialog downloadDialog;

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
        tvVersion.setText("V" + AppUtils.getAppVersionName());
    }

    @Override
    protected void initDate() {
        try {
            tvSize.setText(CacheDataManager.getTotalCacheSize(mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.rl_number_and, R.id.rl_read, R.id.rl_cjian, R.id.rl_clear, R.id.rl_check, R.id.rl_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_number_and://跳转账号安全
                ActivityUtils.startActivity(mContext, AccountActivity.class);
                break;
            case R.id.rl_read:
                ActivityUtils.startActivity(mContext, ReadActivity.class);
                break;
            case R.id.rl_cjian://跳转插件页面
                ActivityUtils.startActivity(mContext, ChaJianActivity.class);
                break;
            case R.id.rl_clear://清除缓存
                NiceDialog.init()
                        .setLayoutId(R.layout.dialog_clear_show)
                        .setConvertListener(new ViewConvertListener() {
                            @Override
                            protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        try {
                                            new Thread(new clearCache()).start();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
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
            case R.id.rl_check://检查更新
                cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
                cPresenter.requestData2(this, cMap, Address.checkForUpdates);
                break;
            case R.id.rl_login_out://退出登录
                NiceDialog.init()
                        .setLayoutId(R.layout.dialog_login_out)     //设置dialog布局文件
                        .setConvertListener(new ViewConvertListener() {
                            @Override
                            protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                holder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {// TODO: 2019/8/21 打开登陆页面 
                                        dialog.dismiss();
                                        loginOut();
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

    /**
     * 退出登录
     */
    public void loginOut() {
        paramMap = new HashMap<>();
        paramMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + Address.loginout, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(getBaseContext()) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                if (weatherResult.getCode() == 200) {
                    AppActivityUtils.StartLoginTaskActivity(mContext);
                } else {
                    showToast("" + weatherResult.getMsg());
                }
            }

            @Override
            public void onAfterFailure() {
                showToast("请求失败");
            }
        });
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        if (weatherResult.getCode() == 200) {// TODO: 2019/8/29设置
            VersionBean sqBean = gson.fromJson(gson.toJson(weatherResult.getData()), VersionBean.class);
            if (sqBean != null && sqBean.getAppVersion() != null) {
                int currentVersion = getVersionCode();
                int lastVersion = NumberFormatUtils.getIntegerByString(sqBean.getAppVersion().getVersionCode());
                if (lastVersion > currentVersion) {
                    NiceDialog.init()
                            .setLayoutId(R.layout.dialog_update)
                            .setConvertListener(new ViewConvertListener() {
                                @Override
                                protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                    holder.setText(R.id.tv1, "Android " + sqBean.getAppVersion().getVersionNumber());
                                    holder.setText(R.id.tv2, sqBean.getAppVersion().getVersionDescribe());
                                    holder.setOnClickListener(R.id.tv_save, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            RetrofitRequest.fileDownload(sqBean.getAppVersion().getVersionUrl(), new RetrofitRequest.DownloadHandler() {
                                                @Override
                                                public void onBody(ResponseBody body) {
                                                    dialog.dismiss();
                                                }

                                                @Override
                                                public void onProgress(int progress) {
                                                    downloadDialog = NiceDialog.init()
                                                            .setLayoutId(R.layout.dialog_download)
                                                            .setConvertListener(new ViewConvertListener() {
                                                                @Override
                                                                protected void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                                                                    ProgressBar progressBar = holder.getView(R.id.progressBar);
                                                                    progressBar.setProgress(progress);
                                                                    holder.setText(R.id.progressTv, progress + "%");
                                                                }
                                                            });
                                                    downloadDialog.setCancelable(false);
                                                    downloadDialog.show(getSupportFragmentManager());

                                                }

                                                @Override
                                                public void onDownLoadSuccess(File file) {
                                                    if (downloadDialog != null && downloadDialog.getShowsDialog()) {
                                                        downloadDialog.dismiss();
                                                        //AppUtils.installApp(file);
                                                        installApk(mContext, file);
                                                    }
                                                }

                                                @Override
                                                public void onError() {
                                                    dialog.dismiss();
                                                }
                                            });
                                        }
                                    });
                                }
                            })
                            .setMargin(40)
                            .show(getSupportFragmentManager());
                } else {
                    NiceDialog.init()
                            .setLayoutId(R.layout.dialog_show_toast)
                            .setMargin(60)
                            .show(getSupportFragmentManager());
                }

            }
        }
    }

    @Override
    public void onError() {
        showToast("请求失败");
    }
    /**
     * 获取版本名
     * @return
     */
    private String getVersionName() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取版本号
     * @return
     */
    private int getVersionCode() {
        try {
            String pkName = this.getPackageName();
            int versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionCode;
            return versionName;
        } catch (Exception e) {
        }
        return 0;
    }

    class clearCache implements Runnable {
        @Override
        public void run() {
            try {
                CacheDataManager.clearAllCache(SettingActivity.this);

                if (CacheDataManager.getTotalCacheSize(SettingActivity.this).startsWith("0")) {
                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    /**
     * 清除缓存
     */
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showToast(getString(R.string.clear2));
                    try {
                        tvSize.setText(CacheDataManager.getTotalCacheSize(SettingActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    };

    /**
     * apk下载安装
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
