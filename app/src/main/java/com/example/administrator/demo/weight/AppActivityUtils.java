package com.example.administrator.demo.weight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.demo.activity.my.TheInfoActivity;
import com.example.administrator.demo.activity.record.JyXqActivity;
import com.example.administrator.demo.activity.setting.MoreActivity;
import com.example.administrator.demo.activity.setting.Phone2Activity;
import com.example.administrator.demo.activity.setting.Pwd2Activity;
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
     * 跳转交易详情页面
     */
    public static void StartJxxqActivity(Context context, String content) {
        Bundle bundle = new Bundle();
        bundle.putString("CONTENT", content);//Tab标识
        startActivity(context, JyXqActivity.class, bundle);
    }

    /**
     * 跳转
     */
    public static void StartPwdActivity(Context context, String content, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("CONTENT", content);//Tab标识
        bundle.putString("ID", id);//Tab标识
        startActivity(context, Pwd2Activity.class, bundle);
    }

    /**
     * 跳转
     */
    public static void StartPhoneActivity(Context context, String content, String id, String pho) {
        Bundle bundle = new Bundle();
        bundle.putString("CONTENT", content);//Tab标识
        bundle.putString("ID", id);//Tab标识
        bundle.putString("PHONE", pho);//Tab标识
        startActivity(context, Phone2Activity.class, bundle);
    }

    /**
     * 跳转他人资料详情页面
     */
    public static void StartTheActivity(Context context, String content) {
        Bundle bundle = new Bundle();
        bundle.putString("CONTENT", content);//Tab标识
        startActivity(context, TheInfoActivity.class, bundle);
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
