package com.example.baselibrary;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * Created by xueliang on 2017/7/26.
 */

public class NumberFormatUtils {
    /**
     * String转int
     * @param str
     * @return
     */
    public static int getIntegerByString(String str){
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        if (str.contains(".")) {
           return (int) getFloatByString(str);
        }
        return Integer.parseInt(str);
    }

    /**
     * String转float
     * @param str
     * @return
     */
    public static float getFloatByString(String str){
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        return Float.parseFloat(str);
    }

    /**
     * String转double
     * @param str
     * @return
     */
    public static double getDoubleByString(String str){
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        return Double.parseDouble(str);
    }

    /**
     * String转int
     * @param str
     * @return
     */
    public static int getFloatByInt(String str){
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        return Integer.parseInt(str);
    }

    /**
     * str 转 double  四舍五入  保留两位小数
     *
     * @return
     */
    public static double getDoubleForString(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        if (isDouble(str)) {
            double db = Double.parseDouble(str);
            BigDecimal b = new BigDecimal(db);
            double d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return d;
        }
        return 0;
    }

    /**
     * str 转 float  四舍五入  保留两位小数
     *
     * @return
     */
    public static float getFloatForString(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        if (isFloat(str)) {
            float db = Float.parseFloat(str);
            BigDecimal b = new BigDecimal(db);
            float d = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            return d;
        }
        return 0;
    }

    /**
     * float 转 int  四舍五入
     *
     * @return
     */
    public static int getFloatForInt(float f) {
            BigDecimal b = new BigDecimal(f);
            int d = b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            return d;
    }

    public static int getIntegerForString(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 四舍五入  保留两位小数
     *
     * @param db
     * @return
     */
    public static float getForFloat(float db) {
        BigDecimal b = new BigDecimal(db);
        float d = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return d;
    }

    /**
     * 保留两位小数格式化
     * @return
     */
    public static String keepTwoDiagits(String srcStr){
        if (TextUtils.isEmpty(srcStr)) {
            srcStr = "0";
        }
        BigDecimal b = new BigDecimal(srcStr);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//        return srcStr;
    }
    /**
     * 四舍五入  保留两位小数
     *
     * @param db
     * @return
     */
    public static double getForDouble(double db) {
        BigDecimal b = new BigDecimal(db);
        double d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    /**
     * 判断字符是否double类型
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
        }
        return false;
    }

    /**
     * 判断字符是否double类型
     *
     * @param str
     * @return
     */
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException ex) {
        }
        return false;
    }



}
