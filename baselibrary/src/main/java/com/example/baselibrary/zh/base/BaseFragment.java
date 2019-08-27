package com.example.baselibrary.zh.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baselibrary.R;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonPresenter;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.utils.StatusBar;
import com.example.baselibrary.zh.utils.TUtil;
import com.example.baselibrary.zh.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>
 * Fragment基类，封装了懒加载的实现
 * <p>
 * 1、Viewpager + Fragment情况下，fragment的生命周期因Viewpager的缓存机制而失去了具体意义
 * 该抽象类自定义一个新的回调方法，当fragment可见状态改变时会触发的回调方法
 *
 * @see #onFragmentVisibleChange(boolean)
 * @see #onFragmentFirstVisible()
 */
public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {
    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;
    private boolean isPause;
    private View rootView;
    private static final String TAG = "BaseFragment";
    protected Unbinder mBinder;
    protected Context mContext;
    protected int mRefreshPage = 1;
    protected int mRefreshCount = 20;
    protected Toolbar mToolbar;
    public P mPresenter;
    public M mModel;
    public CommonPresenter cPresenter;
    public Map<String, String> cMap;
    protected final int DEFAULT_STATUS_BAR_ALPHA = 0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: " + (rootView == null) + "---getUserVisibleHint()" + getUserVisibleHint() + "---isFirstVisible" + isFirstVisible);
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (rootView == null) {
            rootView = view;
            mBinder = ButterKnife.bind(this, rootView);
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView && rootView != null ? rootView : view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this instanceof BaseView) {
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
            mPresenter.attachVM(this, mModel);
            mPresenter.setContext(getActivity());
        }

        if (this instanceof CommonView) {
            cPresenter = new CommonPresenter((CommonView) this);
            cMap = new HashMap<>();
        }
        if (getContentViewLayoutID() != 0) {
            mContext = getActivity();
            return inflater.inflate(getContentViewLayoutID(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }


    protected abstract int getContentViewLayoutID();

    /**
     * 设置沉浸式标题栏 标题栏的颜色
     */
    public void setStatusBarColor(int color) {
        StatusBar.setStatusBarColor(getActivity(), ContextCompat.getColor(mContext, color), DEFAULT_STATUS_BAR_ALPHA);
        StatusBar.changeToLightStatusBar(getActivity());
    }

    /**
     * 设置沉浸式标题栏 里面是Fragment
     */
    public void setStatusBarColorInFragment() {
        StatusBar.translucentStatusBar(getActivity());
    }

    public void setStatusBarColorToLight() {
        StatusBar.translucentStatusBar(getActivity(), true);
        StatusBar.changeToLightStatusBar(getActivity());
    }

    @Override
    public void onDestroy() {
        initVariable();
        mBinder.unbind();
        if (null != mPresenter && null != mModel && this instanceof BaseView)
            mPresenter.detachVM();
        super.onDestroy();
    }


    protected void showToast(Object message) {
        ToastUtils.showToast(mContext, message instanceof String ? message + "" : getResources().getString((int) message));
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
        if (refreshLayout.getState() == RefreshState.Refreshing)
            refreshLayout.finishRefresh();
        else if (refreshLayout.getState() == RefreshState.Loading)
            refreshLayout.finishLoadMore();
        refreshLayout.setEnableLoadMore(isLoadMore);
    }

    /**
     * 设置标题
     */
    protected void setTitleBar(String title, View.OnClickListener listener) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.common_toolbar);
        if (!TextUtils.isEmpty(title))
            ((TextView) rootView.findViewById(R.id.common_toolBar_title)).setText(title);
        mToolbar.setNavigationIcon(R.drawable.black_back);
        mToolbar.setNavigationOnClickListener(listener);
    }

    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i(TAG, "isFirstVisible: " + isFirstVisible + "--isVisibleToUser" + isVisibleToUser + "--rootView == null" + (rootView == null));
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }


    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        isPause = false;
        rootView = null;
        isReuseView = true;
    }

    @Override
    public void onPause() {
        isPause = true;
        super.onPause();
    }

    @Override
    public void onResume() {
        if (isPause && getUserVisibleHint())
            onFragmentVisibleChange(true);
        super.onResume();
    }

    /**
     * @param isReuse
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     * <p>
     * 可在该回调方法里进行一些ui显示与隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected abstract void onFragmentVisibleChange(boolean isVisible);

    /**
     * 在fragment首次可见时回调，可用于加载数据，防止每次进入都重复加载数据
     */
    protected abstract void onFragmentFirstVisible();

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }
}