package com.example.baselibrary.zh.callback;

/**
 * 刷新的回调
 */

public interface RefreshCallBack {
    /**
     * @param stat  1刷新 2加载更多
     * @param page  当前页数
     * @param count 当前count
     */
    void getRefreshDate(int stat, int page, int count);
}
