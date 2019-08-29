package com.example.administrator.demo.weight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.demo.activity.setting.MoreActivity;
import com.example.administrator.demo.activity.user.UserLoginActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;

public class AppActivityUtils extends ActivityUtils {


    /**
     * 跳转帮助页面
     */
    public static void StartMoreActivity(Context context, String content) {
        Bundle bundle = new Bundle();
        bundle.putString("CONTENT", content);//Tab标识
        startActivity(context, MoreActivity.class, bundle);
    }

    /**
     * 跳转login页面,关闭所有打开activity
     */
    public static void StartLoginTaskActivity(Context context) {
        Intent intent = new Intent(context, UserLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
