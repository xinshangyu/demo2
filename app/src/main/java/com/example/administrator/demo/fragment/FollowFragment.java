package com.example.administrator.demo.fragment;

import android.os.Bundle;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseFragment;

public class FollowFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static FollowFragment newInstance(String param1, String param2) {
        FollowFragment fragment = new FollowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_fund_details;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) setStatusBarColorInFragment();
    }

    @Override
    protected void onFragmentFirstVisible() {

    }
}
