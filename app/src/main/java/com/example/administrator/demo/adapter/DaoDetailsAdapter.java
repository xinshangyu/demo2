package com.example.administrator.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.demo.R;
import com.example.administrator.demo.entity.DaojuBean;
import com.example.administrator.demo.weight.AppActivityUtils;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：FundDetailsAdapter
 */
public class DaoDetailsAdapter extends BaseQuickAdapter<List<DaojuBean.PropsAssetsBean>, BaseViewHolder> {

    public DaoDetailsAdapter(List<List<DaojuBean.PropsAssetsBean>> datas) {
        super(R.layout.item_daoju, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, List<DaojuBean.PropsAssetsBean> item) {
        RecyclerView recyclerView = holder.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ItemAdapter itemAdapter = new ItemAdapter(item);
        recyclerView.setAdapter(itemAdapter);

    }

    class ItemAdapter extends BaseQuickAdapter<DaojuBean.PropsAssetsBean, BaseViewHolder> {
        private List<DaojuBean.PropsAssetsBean> datas;
        public ItemAdapter(@Nullable List<DaojuBean.PropsAssetsBean> data) {
            super(R.layout.item_daojuchild, data);
            datas = data;
        }

        @Override
        protected void convert(BaseViewHolder helper, DaojuBean.PropsAssetsBean item) {
            helper.setText(R.id.tv_date, item.getPropName());
            helper.setText(R.id.tv_money, item.getPropNumber());
            if(helper.getAdapterPosition() == (datas.size() - 1)){
                helper.setGone(R.id.sp, true);
            }else{
                helper.setGone(R.id.sp, false);

            }

        }
    }
}
