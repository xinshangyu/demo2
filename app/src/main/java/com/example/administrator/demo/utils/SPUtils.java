package com.example.administrator.demo.utils;

import android.content.Context;

import com.example.administrator.demo.entity.MyModularBen;
import com.example.baselibrary.zh.net.JsonUtils;
import com.example.baselibrary.zh.utils.BaseSPUtils;

public class SPUtils extends BaseSPUtils {

    /**
     * 存用户信息
     *
     * @param context
     * @param json
     */
    public static void setUserInfo(Context context, String json) {
        put(context, USER_INFO_KEY, json);
    }

    /**
     * 拿用户信息
     *
     * @param context
     */
    public static MyModularBen.DataBean.UserInfoBean getUserInfo(Context context) {
        return JsonUtils.getInstance().fromJson((String) get(context, USER_INFO_KEY, ""), MyModularBen.DataBean.UserInfoBean.class);
    }

    /**
     * 存服务器类型
     *
     * @param context
     * @param isTestService
     */
    public static void setNetServiceType(Context context, boolean isTestService) {
        put(context, NET_SERVICE_TEST, isTestService);
    }

    /**
     * 拿服务器类型
     *
     * @param context
     */
    public static boolean geNetServiceType(Context context) {
        return (boolean) get(context, NET_SERVICE_TEST, true);
    }

}
