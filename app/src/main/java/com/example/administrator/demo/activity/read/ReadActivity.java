package com.example.administrator.demo.activity.read;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.Read1Adapter;
import com.example.administrator.demo.adapter.Read2Adapter;
import com.example.baselibrary.zh.base.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;

/**
 * 阅读偏好
 */
public class ReadActivity extends BaseActivity {

    @BindView(R.id.RecyclerView)
    RecyclerView RecyclerView;
    @BindView(R.id.fl_search)
    TagFlowLayout flowLayout;

    private ArrayList<String> mListData = new ArrayList<>();
    private ArrayList<String> mListData2 = new ArrayList<>();
    private Read1Adapter adapter;

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

        flowLayout.setAdapter(new TagAdapter<String>(mListData2) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = View.inflate(mContext,R.layout.item_search_cache, null);
                TextView textView = view.findViewById(R.id.tv_name);
                textView.setText(s);
                return view;
            }

            @Override
            public void onSelected(int position, View view) {
                TextView textView = view.findViewById(R.id.tv_name);
                textView.setBackground(getResources().getDrawable(R.drawable.shape_r6));
                super.onSelected(position, view);
            }

            @Override
            public void unSelected(int position, View view) {
                TextView textView = view.findViewById(R.id.tv_name);
                textView.setBackground(getResources().getDrawable(R.drawable.shape_r7));
                super.unSelected(position, view);
            }
        });

        flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
//                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });

    }
}
