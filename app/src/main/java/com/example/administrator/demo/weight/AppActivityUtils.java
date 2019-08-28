package com.example.administrator.demo.weight;

import android.content.Context;
import android.os.Bundle;

import com.example.administrator.demo.activity.setting.MoreActivity;
import com.example.baselibrary.zh.utils.ActivityUtils;

public class AppActivityUtils extends ActivityUtils {


    /**
     * 跳转帮助页面
     */
    public static void StartMoreActivity(Context context, String content, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("CONTENT", content);//Tab标识
        bundle.putString("TITLE", title);//标题
        startActivity(context, MoreActivity.class, bundle);
    }

    /**
     * 跳转login页面,关闭所有打开activity
     */
    public static void StartLoginTaskActivity(Context context) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
    }
}
