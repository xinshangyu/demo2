package com.example.baselibrary.zh.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.example.baselibrary.zh.CommonBaseLibrary;

import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/23
 *     desc  : Activity相关工具类
 * </pre>
 */
public class ActivityUtils {

    public ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 启动Activity
     *
     * @param context
     * @param clz
     * @param bundle
     */
    public static void startActivity(@NonNull Context context, @NonNull Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        if (null != bundle) intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 启动Activity
     *
     * @param context
     * @param clz
     */
    public static void startActivity(@NonNull Context context, @NonNull Class<?> clz) {
        startActivity(context, clz, null);
    }

    /**
     * 启动Activity
     *
     * @param activity
     * @param clz
     * @param bundle
     */
    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<?> clz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, clz);
        if (null != bundle) intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动Activity
     *
     * @param activity
     * @param clz
     */
    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<?> clz, int requestCode) {
        startActivityForResult(activity, clz, null, requestCode);
    }
//    /**
//     * 跳转登录界面
//     *
//     * @param context
//     * @param finishOthers 是否结束其他的activity true结束 false 不结束
//     */
//    public static void openLoginActivity(@NonNull Context context, boolean finishOthers) {
//        Intent intent = new Intent(context, LoginMainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putBoolean("finishOthers", finishOthers);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
//    }

//    /**
//     * 启动前必须登录Activity
//     *
//     * @param context
//     * @param clz
//     */
//    public static void startActivityByLogin(@NonNull Context context, @NonNull Class<?> clz) {
//        startActivityByLogin(context, clz, null);
//    }


    /**
     * 回到桌面
     */
    public static void startHomeActivity(Context context) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(homeIntent);
    }

    /**
     * 获取Activity栈链表
     *
     * @return Activity栈链表
     */
    public static List<Activity> getActivityList() {
        return CommonBaseLibrary.activityList;
    }

    /**
     * 获取启动项Activity
     *
     * @param packageName 包名
     * @return 启动项Activity
     */
    public static String getLauncherActivity(@NonNull final String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PackageManager pm = CommonBaseLibrary.getApplication().getPackageManager();
        List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo aInfo : info) {
            if (aInfo.activityInfo.packageName.equals(packageName)) {
                return aInfo.activityInfo.name;
            }
        }
        return "no " + packageName;
    }

    /**
     * 获取栈顶Activity
     *
     * @return 栈顶Activity
     */
    public static Activity getTopActivity() {
        if (CommonBaseLibrary.topActivity != null) {
            Activity activity = CommonBaseLibrary.topActivity.get();
            if (activity != null) {
                return activity;
            }
        }
        List<Activity> activities = CommonBaseLibrary.activityList;
        int size = activities.size();
        return size > 0 ? activities.get(size - 1) : null;
    }

    /**
     * 判断Activity是否存在栈中
     *
     * @param activity activity
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isActivityExistsInStack(@NonNull final Activity activity) {
        List<Activity> activities = CommonBaseLibrary.activityList;
        for (Activity aActivity : activities) {
            if (aActivity.equals(activity)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断Activity是否存在栈中
     *
     * @param clz activity类
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isActivityExistsInStack(@NonNull final Class<?> clz) {
        List<Activity> activities = CommonBaseLibrary.activityList;
        for (Activity aActivity : activities) {
            if (aActivity.getClass().equals(clz)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束Activity
     *
     * @param activity activity
     */
    public static void finishActivity(@NonNull final Activity activity) {
        activity.finish();
    }


    /**
     * 结束Activity
     *
     * @param clz activity类
     */
    public static void finishActivity(@NonNull final Class<?> clz) {
        List<Activity> activities = CommonBaseLibrary.activityList;
        for (Activity activity : activities) {
            if (activity.getClass().equals(clz)) {
                activity.finish();
            }
        }
    }

    /**
     * 结束到指定Activity
     *
     * @param activity      activity
     * @param isIncludeSelf 是否结束该activity自己
     */
    public static boolean finishToActivity(@NonNull final Activity activity,
                                           final boolean isIncludeSelf) {
        List<Activity> activities = CommonBaseLibrary.activityList;
        for (int i = activities.size() - 1; i >= 0; --i) {
            Activity aActivity = activities.get(i);
            if (aActivity.equals(activity)) {
                if (isIncludeSelf) {
                    finishActivity(aActivity);
                }
                return true;
            }
            finishActivity(aActivity);
        }
        return false;
    }

    /**
     * 结束到指定Activity
     *
     * @param isIncludeSelf 是否结束该activity自己
     */
    public static boolean finishToActivity(@NonNull final Class<?> clz,
                                           final boolean isIncludeSelf) {
        List<Activity> activities = CommonBaseLibrary.activityList;
        for (int i = activities.size() - 1; i >= 0; --i) {
            Activity aActivity = activities.get(i);
            if (aActivity.equals(clz)) {
                if (isIncludeSelf) {
                    finishActivity(aActivity);
                }
                return true;
            }
            finishActivity(aActivity);
        }
        return false;
    }

    /**
     * 结束除最新之外的同类型Activity
     * <p>也就是让栈中最多只剩下一种类型的Activity</p>
     *
     * @param clz activity类
     */
    public static void finishOtherActivitiesExceptNewest(@NonNull final Class<?> clz) {
        List<Activity> activities = CommonBaseLibrary.activityList;
        boolean flag = false;
        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = activities.get(i);
            if (activity.getClass().equals(clz)) {
                if (flag) {
                    finishActivity(activity);
                } else {
                    flag = true;
                }
            } else {
                finishActivity(activity);
            }
        }
    }

    /**
     * 清除除本activity之外所有的activity
     *
     * @param clz 本activity的类名
     */
    public static void finishOtherActivities(@NonNull final Class<?> clz) {
        List<Activity> activities = CommonBaseLibrary.activityList;
        for (Activity activity : activities) {
            if (!activity.getClass().equals(clz)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有activity
     */
    public static void finishAllActivities() {
        List<Activity> activityList = CommonBaseLibrary.activityList;
        for (int i = activityList.size() - 1; i >= 0; --i) {// 从栈顶开始移除
            Activity activity = activityList.get(i);
            activity.finish();// 在onActivityDestroyed发生remove
        }
    }

    private static Context getActivityOrApp() {
        Activity topActivity = getTopActivity();
        return topActivity == null ? CommonBaseLibrary.getApplication() : topActivity;
    }


}