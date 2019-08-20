package com.example.baselibrary;


import android.text.TextUtils;
import android.util.Log;

import java.util.logging.Logger;

public class LogUtil {
    private static final String TAG = "wyz";   //过滤日志的TAG
    private static boolean isDebug_i = true;
    private static boolean isDebug_d = true;
    private static boolean isDebug_e = true;
    private static int LOG_MAXLENGTH = 2000;

    public static void d() {
        if (isDebug_d) {
            Log.d(TAG, getLocation());
        }
    }

    public static void d(String msg) {
        if (isDebug_d) {
            Log.d(TAG, getLocation() + msg);
        }
    }

    public static void i(String msg) {
        if (isDebug_i) {
            Log.i(TAG, getLocation() + msg);
        }
    }

    public static void i() {
        if (isDebug_i) {
            Log.i(TAG, getLocation());
        }
    }

    public static void e(String msg) {
        if (isDebug_e) {
            Log.e(TAG, getLocation() + msg);
        }
    }

    public static void e(String msg, Throwable e) {
        if (isDebug_e) {
            Log.e(TAG, getLocation() + msg, e);
        }
    }

    public static void e(Throwable e) {
        if (isDebug_e) {
            Log.e(TAG, getLocation(), e);
        }
    }

    public static void e(String 全部删除, String string) {
        if (isDebug_e) {
            Log.e(TAG, getLocation());
        }
    }

    private static String getLocation() {
        final String className = Logger.class.getName();
        final StackTraceElement[] traces = Thread.currentThread()
                .getStackTrace();

        boolean found = false;

        for (StackTraceElement trace : traces) {
            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":"
                                + trace.getMethodName() + ":"
                                + trace.getLineNumber() + "]: ";
                    }
                } else if (trace.getClassName().startsWith(className)) {
                    found = true;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }

    public static void i(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }




}
