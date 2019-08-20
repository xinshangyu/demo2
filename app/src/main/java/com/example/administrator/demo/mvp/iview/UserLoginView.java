package com.example.administrator.demo.mvp.iview;


/**
 * 用户注册
 **/
public interface UserLoginView {
    void onLoginStatus(int status, String msg, int isFirstLogin); //注册返回状态
}
