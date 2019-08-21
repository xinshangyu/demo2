package com.example.administrator.demo.activity.user;

import android.widget.TextView;

import com.example.administrator.demo.MainActivity;
import com.example.administrator.demo.R;
import com.example.administrator.demo.base.BaseActivity;
import com.example.administrator.demo.mvp.iview.UserLoginView;
import com.example.administrator.demo.mvp.presenter.UserLoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class UserLoginActivity extends BaseActivity implements UserLoginView {
    //账号
    @BindView(R.id.tv_user_account)
    TextView userAccount;
    //密码
    @BindView(R.id.tv_user_password)
    TextView userPassword;
    private UserLoginPresenter userLoginPresenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    public void initView() {
        userLoginPresenter = new UserLoginPresenter(this);
    }

    /***
     * 登录操作
     */
    @OnClick(R.id.bt_to_login)
    void onToLogin() {
        userLoginPresenter.requestRanking(getApplicationContext(), userAccount.getText().toString(), userPassword.getText().toString());

    }


    /**
     * 登录
     *
     * @param status
     * @param msg
     * @param isFirstLogin
     */
    @Override
    public void onLoginStatus(int status, String msg, int isFirstLogin) {
        startActivity(MainActivity.class, 1);
    }
}
