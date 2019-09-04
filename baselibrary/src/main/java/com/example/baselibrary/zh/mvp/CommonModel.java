package com.example.baselibrary.zh.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Window;

import com.example.baselibrary.LogUtil;
import com.example.baselibrary.ToastUtils;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.zh.network.RetrofitRequest;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.google.gson.Gson;

import java.util.Map;

public class CommonModel {
    private ProgressDialog dialog;

    private void initDialog(Context activity, String dialogMessage) {
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(dialogMessage);
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void showDialog(Context activity, String dialogMessage) {
        initDialog(activity, TextUtils.isEmpty(dialogMessage) ? " 疯狂加载中......" : dialogMessage);
    }

    public void showDialog(Context activity) {
        initDialog(activity, " 疯狂加载中......");
    }

    public void dissDialog(Context activity) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private CommonPresenter commonPresenter;

    public CommonModel(CommonPresenter commonPresenter) {
        this.commonPresenter = commonPresenter;
    }

    public void getData(final Context context, Map<String, String> paramMap, String url) {
        showDialog(context);
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + url, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                dissDialog(context);
                LogUtil.e("ldh 返回数据1" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 200) {//code==200不是等于1
                    commonPresenter.toData(weatherResult);
                    return;
                }
            }

            @Override
            public void onAfterFailure() {
                dissDialog(context);
                commonPresenter.toError();
                ToastUtils.showShort(context, "请求失败");
            }
        });
    }

    /**
     * 不显示dialog
     *
     * @param context
     * @param paramMap
     * @param url
     */
    public void getData2(final Context context, Map<String, String> paramMap, String url) {
        RetrofitRequest.sendPostRequest(ApiKeys.getApiUrl() + url, paramMap, WeatherResult.class, new RetrofitRequest.ResultHandler<WeatherResult>(context) {
            @Override
            public void onBeforeResult() {

            }

            @Override
            public void onResult(WeatherResult weatherResult) {
                LogUtil.e("ldh 返回数据1" + new Gson().toJson(weatherResult));
                if (weatherResult.getCode() == 200) {//code==200不是等于1
                    commonPresenter.toData(weatherResult);
                    return;
                }
            }

            @Override
            public void onAfterFailure() {
                commonPresenter.toError();
                ToastUtils.showShort(context, "请求失败");
            }
        });
    }
}
