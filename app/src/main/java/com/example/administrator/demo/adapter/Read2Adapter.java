package com.example.administrator.demo.adapter;

import android.content.Context;
import android.os.Bundle;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.adapter.CommonAdapter;
import com.example.baselibrary.zh.adapter.base.ViewHolder;
import com.example.baselibrary.zh.utils.ActivityUtils;
import com.example.baselibrary.zh.utils.flowlayout.FlowLayout;
import com.example.baselibrary.zh.utils.flowlayout.FlowLayoutAdapter;

import java.util.ArrayList;

public class Read2Adapter extends CommonAdapter<String> {

    private Context context;
    private ArrayList<String> datas;

    public Read2Adapter(Context context, ArrayList<String> datas) {
        super(context, R.layout.item_read2, datas);
        this.context = context;
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder holder, String messageListBean, int position) {
        FlowLayout flowLayout = holder.getView(R.id.fl_search);

//        List<SearchBean.Regions.Desc> list = Imgs.getList();
//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i < mDatas.size(); i++) {
//            strings.add(messageListBean);
//        }

        FlowLayoutAdapter<String> flowLayoutAdapter = new FlowLayoutAdapter<String>(datas) {
            @Override
            public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position, String bean) {
                holder.setText(R.id.tv_name, bean);
            }

            @Override
            public void onItemClick(int position, String bean) {
                Bundle bundle = new Bundle();

//                bundle.putString(AppConfig.NAME,list.get(position).getName());
//                bundle.putString(AppConfig.MID,list.get(position).getMid());
//                bundle.putBoolean(AppConfig.ISAREA,true);
//                ActivityUtils.startActivity(mContext,SearchAreaActivity.class,bundle);
            }

            @Override
            public int getItemLayoutID(int position, String bean) {
                return R.layout.item_search_cache;
            }
        };
        flowLayout.setAdapter(flowLayoutAdapter);


    }
}
