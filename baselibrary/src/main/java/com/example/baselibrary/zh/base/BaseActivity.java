package com.example.baselibrary.zh.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.baselibrary.R;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonPresenter;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.utils.StatusBar;
import com.example.baselibrary.zh.utils.TUtil;
import com.example.baselibrary.zh.utils.ToastUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    protected Context mContext;
    protected Unbinder mBinder;
    protected final int DEFAULT_STATUS_BAR_ALPHA = 0;
    private static final String TAG = "BaseActivity";
    protected Toolbar mToolbar;
    protected int mRefreshPage = 1;
    protected int mRefreshCount = 20;
    protected P mPresenter = null;
    protected M mModel = null;
    protected CommonPresenter cPresenter = null;

    public Map<String, String> cMap;
    public Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mContext = this;
        setStatusBarColor(R.color.design_default_color_primary);
        mBinder = ButterKnife.bind(this);
        if (this instanceof BaseView) {
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
            mPresenter.attachVM(this, mModel);
            mPresenter.setContext(mContext);
        }

        if (this instanceof CommonView) {
            cPresenter = new CommonPresenter((CommonView) this);
            cMap = new HashMap<>();
            gson = new Gson();
        }
        initView(savedInstanceState);
        initDate();
    }

    /**
     * 设置沉浸式标题栏 标题栏的颜色
     */
    public void setStatusBarColor(int color) {
        StatusBar.setStatusBarColor(this, ContextCompat.getColor(mContext, color), DEFAULT_STATUS_BAR_ALPHA);
        StatusBar.changeToLightStatusBar(this);
    }

    /**
     * 设置沉浸式标题栏 里面是Fragment
     */
    public void setStatusBarColorInFragment() {
        StatusBar.translucentStatusBar(this);
    }

    /**
     * 设置透明沉浸式标题栏
     */
    public void setStatusBarColorToLight() {
        StatusBar.translucentStatusBar(this, true);
        StatusBar.changeToLightStatusBar(this);
    }
    /**
     * 跳转Activity
     * 带参数
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    /**
     * 设置标题
     */
    protected void setTitleBar(String title, View.OnClickListener listener) {
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        if (!TextUtils.isEmpty(title))
            ((TextView) findViewById(R.id.common_toolBar_title)).setText(title);
        mToolbar.setNavigationIcon(R.drawable.black_back);
        mToolbar.setNavigationOnClickListener(listener);
    }

    /**
     * 显示右边标题
     *
     * @param title
     * @param listener
     * @param isVisible
     * @param right
     */
    protected void setTitleBar(String title, View.OnClickListener listener, boolean isVisible, String right) {
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        if (!TextUtils.isEmpty(title))
            ((TextView) findViewById(R.id.common_toolBar_title)).setText(title);
        TextView mTextRight = (TextView) findViewById(R.id.common_toolBar_text_right);
        if (isVisible) {
            mTextRight.setVisibility(View.VISIBLE);
            mTextRight.setText(right);
        }
        mToolbar.setNavigationIcon(R.drawable.black_back);
        mToolbar.setNavigationOnClickListener(listener);
    }


    protected abstract int getLayout();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initDate();

    protected void showToast(Object message) {
        ToastUtils.showToast(this, message instanceof String ? message + "" : getResources().getString((int) message));
    }

    @Override
    protected void onDestroy() {
        mBinder.unbind();
        if (null != mPresenter && null != mModel && this instanceof BaseView)
            mPresenter.detachVM();
        super.onDestroy();
    }


    /**
     * 设置刷新控件
     *
     * @param refreshLayout
     * @param callBack      访问网络回调
     */
    protected void setRefresh(final SmartRefreshLayout refreshLayout, final RefreshCallBack callBack) {
        refreshLayout.setEnableOverScrollDrag(true)
                .setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                    @Override
                    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                        mRefreshPage++;
                        callBack.getRefreshDate(2, mRefreshPage, mRefreshCount);
                    }

                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        mRefreshPage = 1;
                        callBack.getRefreshDate(1, mRefreshPage, mRefreshCount);
                    }
                });
    }

    /**
     * 网络访问完成 刷新控件
     *
     * @param refreshLayout
     * @param isLoadMore    false禁用下拉加载更多
     */
    protected void setFinishRefresh(final SmartRefreshLayout refreshLayout, boolean isLoadMore) {
        if (refreshLayout != null && refreshLayout.getState() == RefreshState.Refreshing)
            refreshLayout.finishRefresh();
        else if (refreshLayout != null && refreshLayout.getState() == RefreshState.Loading)
            refreshLayout.finishLoadMore();
        refreshLayout.setEnableLoadMore(isLoadMore);
    }

    /**
     * 获取Drawable
     *
     * @param id
     * @return
     */
    protected Drawable getDrawableV4(@DrawableRes int id) {
        return ContextCompat.getDrawable(this, id);
    }

    /**
     * 获取Color
     *
     * @param id
     * @return
     */
    protected int getColorV4(@ColorRes int id) {
        return ContextCompat.getColor(this, id);
    }

}