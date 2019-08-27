package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.CommentAdapter;
import com.example.administrator.demo.entity.SCBean;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.base.BaseFragment;
import com.example.baselibrary.zh.callback.RefreshCallBack;
import com.example.baselibrary.zh.mvp.CommonView;
import com.example.baselibrary.zh.network.result.WeatherResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 点赞
 */
public class ZanFragment extends BaseFragment implements RefreshCallBack, CommonView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.ll_bottom)
    LinearLayout mLLBottom;
    @BindView(R.id.tv_save)
    TextView mBtAllDelete;
    @BindView(R.id.tv_delete)
    TextView mBtDelete;

    CommentAdapter mAdapter;
    private ArrayList<SCBean.BizCircleBean> mBeanList = new ArrayList<>();


    public static ZanFragment newInstance(String param1, String param2) {
        ZanFragment fragment = new ZanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        cMap.put("oprType", "01");//收藏
        cPresenter.requestData2(getActivity(), cMap, Address.praisedArticles);
        setRefresh(mSmartRefreshLayout, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CommentAdapter(mContext, mBeanList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                mBeanList.get(position).setIsDetele(!mBeanList.get(position).getIsDetele());
                mBtDelete.setText("删除（1）");
                mAdapter.setShowCheck(true);
                mLLBottom.setVisibility(View.VISIBLE);
                return false;
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.CheckBox_my_collection) {

                    mBeanList.get(position).setIsDetele(!mBeanList.get(position).getIsDetele());
                    int i = 0;
                    for (SCBean.BizCircleBean bean : mBeanList) {
                        if(bean.getIsDetele()){
                            i++;
                        }
                    }
                    mBtDelete.setText("删除（"+ i +"）");
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {

    }

    @Override
    public void onData(WeatherResult weatherResult) {

        SCBean scBean = gson.fromJson(gson.toJson(weatherResult.getData()), SCBean.class);
        if (scBean != null && scBean.getBizCircle() != null && scBean.getBizCircle().size() > 0) {
            mBeanList.addAll(scBean.getBizCircle());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK) {
                    if (mAdapter.getShowCheck()) {
                        mAdapter.setShowCheck(false);
                        mLLBottom.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 删除所有
     **/
    @OnClick(R.id.tv_save)
    void onAllDetele() {

    }

    /**
     * 删除
     **/
    @OnClick(R.id.tv_delete)
    void onDetele() {

    }
}
