package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.CommentAdapter;
import com.example.administrator.demo.entity.QuickReturnTopEvent;
import com.example.administrator.demo.entity.SCBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseFragment;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收藏
 */
public class ScFragment extends BaseFragment implements RefreshCallBack, CommonView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    CommentAdapter mAdapter;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    Unbinder unbinder;
    private ArrayList<SCBean.BizCircleBean> mBeanList = new ArrayList<>();
    private String mVid;


    public static ScFragment newInstance(String param1) {
        ScFragment fragment = new ScFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_follows;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
//        if (isVisible) setStatusBarColorInFragment();
        if (isVisible) mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onFragmentFirstVisible() {

        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cMap.put("oprType", "03");//收藏
        cPresenter.requestData2(getActivity(), cMap, Address.scanCollectionInfo);

        EventBus.getDefault().register(this);

//        for (int i = 0; i < 8; i++) {
//            mBeanList.add("吃多少" + i);
//        }

        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CommentAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvents(QuickReturnTopEvent event) {
        if ("SC".equals(event.current)) {
            llBottom.setVisibility(llBottom.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            mAdapter.setShowCheck(!mAdapter.getShowCheck());
        }
//        else if ("SC2".equals(event.current)) {
//            llBottom.setVisibility(View.GONE);
//            mAdapter.setShowCheck(false);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @OnClick({R.id.tv_save, R.id.tv_delete, R.id.ll_bottom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                showToast("清空");
                break;
            case R.id.tv_delete:
                showToast("删除");
                break;
        }
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        SCBean scBean = gson.fromJson(gson.toJson(weatherResult.getData()), SCBean.class);
        if(scBean != null && scBean.getBizCircle() != null && scBean.getBizCircle().size() > 0){
            mBeanList.addAll(scBean.getBizCircle());
            mAdapter.notifyDataSetChanged();
        }
    }
}
