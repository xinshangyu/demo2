package com.example.administrator.demo.fragment;

import android.os.Bundle;

import com.example.administrator.demo.R;
import com.example.baselibrary.zh.base.BaseFragment;

public class UnFollowFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static UnFollowFragment newInstance(String param1, String param2) {
        UnFollowFragment fragment = new UnFollowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_email_bind;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    protected void onFragmentFirstVisible() {

    }
}
