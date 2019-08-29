package com.example.administrator.demo.activity.read;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.Read1Adapter;
import com.example.administrator.demo.adapter.Read2Adapter;
import com.example.baselibrary.zh.base.BaseActivity;
import com.example.baselibrary.zh.utils.flowlayout.FlowLayout;
import com.example.baselibrary.zh.utils.flowlayout.FlowLayoutAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 阅读偏好
 */
public class ReadActivity extends BaseActivity {

    @BindView(R.id.RecyclerView)
    RecyclerView RecyclerView;
    @BindView(R.id.fl_search)
    FlowLayout flowLayout;

    private ArrayList<String> mListData = new ArrayList<>();
    private ArrayList<String> mListData2 = new ArrayList<>();
    private Read1Adapter adapter;
    private Read2Adapter adapter2;

    @Override
    protected int getLayout() {
        return R.layout.activity_read;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleBar(getResources().getString(R.string.zhhy), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initDate() {

        for (int i = 0; i < 5; i++) {
            mListData.add("的的f" + i);
        }
        for (int i = 0; i < 5; i++) {
            if (i == 0)
                mListData2.add("的的成第三" + i);
            else if (i == 1) mListData2.add("的的成" + i);
            else mListData2.add("的的成ac地方第三" + i);
        }

        RecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new Read1Adapter(mContext, mListData);
        RecyclerView.setAdapter(adapter);

//        RecyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
//        adapter2 = new Read2Adapter(mContext, mListData2);
//        RecyclerView2.setAdapter(adapter2);

        FlowLayoutAdapter<String> flowLayoutAdapter = new FlowLayoutAdapter<String>(mListData2) {
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
