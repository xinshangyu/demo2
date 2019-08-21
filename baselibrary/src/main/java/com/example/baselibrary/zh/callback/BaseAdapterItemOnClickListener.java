package com.example.baselibrary.zh.callback;

import android.view.View;

/**
 * Created by Administrator on 2017/12/5 0005.
 * 适配器的单击事件的回调
 */
enum AdapterItemClickType {
    SingleClick,
    DoubleClick,
    LongClick;
}

public interface BaseAdapterItemOnClickListener<T> {
    void ItemClickListener(View view, int position, T item, AdapterItemClickType adapterItemClickType);
}
