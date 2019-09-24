package com.example.administrator.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.demo.entity.MyModularBen;
import com.example.administrator.demo.weight.ApplicationUtils;
import com.example.baselibrary.zh.net.JsonUtils;
import com.example.baselibrary.zh.utils.BaseSPUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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

    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "ad.pre";

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put( String key, Object object) {

        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, (String) object);

        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get( String key, Object defaultObject) {
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    public static void putBoolean(String key,boolean value){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);

        SharedPreferencesCompat.apply(editor);
    }

    public static boolean getBoolean(String key,boolean defValue){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static int getInt(String key,int defValue){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putInt(String key,int value){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);

        SharedPreferencesCompat.apply(editor);
    }

    public static long getLong(String key,long defValue){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void putLong(String key,long value){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);

        SharedPreferencesCompat.apply(editor);
    }

    public static String getString(String key,String defValue){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putString(String key,String value){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);

        SharedPreferencesCompat.apply(editor);
    }

    public static float getFloat(String key,float defValue){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static void putFloat(String key,float value){
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove( String key) {
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains( String key) {
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = ApplicationUtils.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
