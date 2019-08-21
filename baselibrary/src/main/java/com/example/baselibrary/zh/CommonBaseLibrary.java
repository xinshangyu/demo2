package com.example.baselibrary.zh;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/7/15 0015.
 */

public class CommonBaseLibrary {
    public volatile static CommonBaseLibrary mInstance = null;
    private static Application mApplication = null;
    public static WeakReference<Activity> topActivity;
    public static List<Activity> activityList = new LinkedList<>();
    private static Retrofit mRetrofit;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
    }

    public CommonBaseLibrary() {
    }

    public static CommonBaseLibrary getInstance() {
        if (null == mInstance) {
            synchronized (CommonBaseLibrary.class) {
                if (null == mInstance) {
                    mInstance = new CommonBaseLibrary();
                }
            }
        }
        return mInstance;
    }

    public CommonBaseLibrary init(Application application, String baseUrl) {
        mApplication = application;
        application.registerActivityLifecycleCallbacks(mCallbacks);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return this;
    }

    public static Application getApplication() {
        return mApplication;
    }

    public static Retrofit getRetrofit() {
        if (null == mRetrofit)
            throw new NullPointerException("please call CommonBaseLibrary.getInstance().init() first in application!");
        return mRetrofit;

    }

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activityList.add(activity);
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activityList.remove(activity);
        }
    };

    private static void setTopActivityWeakRef(Activity activity) {
        if (topActivity == null || !activity.equals(topActivity.get())) {
            topActivity = new WeakReference<>(activity);
        }
    }
}
