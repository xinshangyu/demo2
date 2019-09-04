package com.example.administrator.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.demo.R;
import com.example.administrator.demo.adapter.CommentAdapter;
import com.example.administrator.demo.entity.QuickReturnTopEvent;
import com.example.administrator.demo.entity.SCBean;
import com.example.administrator.demo.weight.nice.BaseNiceDialog;
import com.example.administrator.demo.weight.nice.NiceDialog;
import com.example.administrator.demo.weight.nice.ViewConvertListener;
import com.example.administrator.demo.weight.nice.ViewHolder;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.ll_bottom)
    LinearLayout mLLBottom;
    @BindView(R.id.tv_delete)
    TextView mBtDelete;
    @BindView(R.id.rl_empty)
    RelativeLayout rl_empty;

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
        if (isVisible) mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentFirstVisible() {
        EventBus.getDefault().register(this);
        showDialog(getActivity());
        cMap.put("userId", SharedPreferencesHelper.getPrefString("userId", ""));
        cMap.put("oprType", "03");//收藏
        cPresenter.requestData2(getActivity(), cMap, Address.scanCollectionInfo);

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
                        if (bean.getIsDetele()) {
                            i++;
                        }
                    }
                    mBtDelete.setText("删除（" + i + "）");
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvents(QuickReturnTopEvent event) {
        if ("SC".equals(event.current)) {
            mLLBottom.setVisibility(mLLBottom.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            mAdapter.setShowCheck(!mAdapter.getShowCheck());
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getRefreshDate(int stat, int page, int count) {
        setFinishRefresh(mSmartRefreshLayout, false);
    }

    @Override
    public void onData(WeatherResult weatherResult) {
        dissDialog(getActivity());
        SCBean scBean = gson.fromJson(gson.toJson(weatherResult.getData()), SCBean.class);
        if (scBean != null && scBean.getBizCircle() != null && scBean.getBizCircle().size() > 0) {
            rl_empty.setVisibility(View.GONE);
            mBeanList.addAll(scBean.getBizCircle());
            mAdapter.notifyDataSetChanged();
        } else {
            rl_empty.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 删除所有
     **/
    @OnClick(R.id.tv_save)
    void onAllDetele() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_delete_show)     //设置dialog布局文件
                .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
                    @Override
                    public void convertView(ViewHolder viewHolder, final BaseNiceDialog dialog) {
                        viewHolder.setText(R.id.tv_title, "清空提示");
                        viewHolder.setText(R.id.tv_content, "确定要清空吗？清空后将永远无法找回，请谨慎操作");
                        viewHolder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        viewHolder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                mBeanList.clear();
                                mAdapter.setShowCheck(false);
                                mLLBottom.setVisibility(View.GONE);
                            }
                        });
                    }
                })
                .setMargin(60)
                .show(getFragmentManager());
    }

    /**
     * 删除
     **/
    @OnClick(R.id.tv_delete)
    void onDetele() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_delete_show)     //设置dialog布局文件
                .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
                    @Override
                    public void convertView(ViewHolder viewHolder, final BaseNiceDialog dialog) {
                        viewHolder.setOnClickListener(R.id.tv_do_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        viewHolder.setOnClickListener(R.id.tv_start, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                List<SCBean.BizCircleBean> data = new ArrayList<>();
                                for (int i = 0; i < mBeanList.size(); i++) {
                                    if (mBeanList.get(i).getIsDetele()) {
                                        data.add(mBeanList.get(i));
                                    }
                                }
                                if (data.size() <= 0) {
                                    showToast("请选择删除内容");
                                    return;
                                }

                                dialog.dismiss();
                                for (int i = 0; i < data.size(); i++) {
                                    for (int j = 0; j < mBeanList.size(); j++) {
                                        if (mBeanList.get(j).getId().equals(data.get(i).getId())) {
                                            mBeanList.remove(j);
                                            break;
                                        }
                                    }
                                }
                                mAdapter.setShowCheck(false);
                                mLLBottom.setVisibility(View.GONE);
                            }
                        });
                    }
                })
                .setMargin(60)
                .show(getFragmentManager());
    }
}
