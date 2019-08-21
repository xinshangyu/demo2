package com.example.baselibrary.zh.base;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wng on 2017/2/16.
 */

public abstract class BasePresenter<M, V> {
    public M mModel;
    public V mView;
    protected Context mContext;

    public void attachVM(V v, M m) {
        this.mModel = m;
        this.mView = v;
    }

    public <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void detachVM() {
        mView = null;
        mModel = null;
    }
}
