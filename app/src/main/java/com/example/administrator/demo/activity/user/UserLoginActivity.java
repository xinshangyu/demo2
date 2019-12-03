package com.example.administrator.demo.activity.user;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.administrator.demo.MainActivity;
import com.example.administrator.demo.R;
import com.example.administrator.demo.base.BaseActivity;
import com.example.administrator.demo.mvp.iview.UserLoginView;
import com.example.administrator.demo.mvp.presenter.UserLoginPresenter;
import com.example.administrator.demo.utils.SPUtils;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.zh.utils.ToastUtils;
import com.google.gson.Gson;
import com.vincent.filepicker.filter.FileFilter;
import com.vincent.filepicker.filter.callback.FilterResultCallback;
import com.vincent.filepicker.filter.entity.Directory;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

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
        requestPermission(Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE);
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
        SPUtils.put("Phone", userAccount.getText().toString());
        startActivity(MainActivity.class, 1);
    }

    @Override
    public void onLoginError(String msg) {
        ToastUtils.showToast(getApplicationContext(), "" + msg);
    }

    /**
     * 权限申请
     *
     * @param permissions
     */
    private void requestPermission(String... permissions) {
        AndPermission.with(this).runtime().permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        loadData();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(UserLoginActivity.this, permissions)) {
                        }
                    }
                }).start();
    }

    private void loadData() {
        FileFilter.getFiles(this, new FilterResultCallback<NormalFile>() {
            @Override
            public void onResult(List<Directory<NormalFile>> directories) {
                Gson gson = new Gson();
                String json = gson.toJson(directories);
                SharedPreferencesHelper.setPrefString("typeFiles", json);
            }
        }, new String[]{"xlsx", "xls", "doc", "docx", "ppt", "pptx", "pdf", "epub"});
    }
}
