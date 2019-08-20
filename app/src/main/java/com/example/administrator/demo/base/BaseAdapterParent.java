package com.example.administrator.demo.base;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract  class BaseAdapterParent<T> extends BaseAdapter {
    private List<T> mDatas;
    private Context mContext;
    private int layoutID;
    private ViewHolder holder;
    private FragmentManager context;
    private int tag;
    public BaseAdapterParent(List<T> mDatas, Context mContext, int layoutID) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.layoutID = layoutID;
    }
    public BaseAdapterParent(List<T> mDatas, Context mContext, int layoutID, FragmentManager context) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.layoutID = layoutID;
        this.context=context;
    }

    public BaseAdapterParent(List<T> mDatas, Context mContext, int layoutID, int tag) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.layoutID = layoutID;
        this.tag=tag;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            holder = ViewHolder.getHolder(mContext, convertView, layoutID, parent);


        try {
            setContent(holder, mDatas, position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return holder.getConverview();
    }
    public abstract void setContent(ViewHolder holder, List<T> mDatas, int position) throws Exception;
}
