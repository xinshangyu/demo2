package com.example.administrator.demo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.administrator.demo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StringToIconUtils {

    public static SpannableString strToIcon(Context context, String text){

        SpannableString spannableString = new SpannableString(text);
        List<Integer> datas1 = getIndex(text, "1");
        if(datas1 != null && datas1.size() > 0){
            for (int i = 0; i < datas1.size(); i++){
                Drawable drawable = context.getResources().getDrawable(R.mipmap.qiye_vip1);
                drawable.setBounds(0, 0, 42, 42);
                ImageSpan imageSpan = new ImageSpan(drawable);

                spannableString.setSpan(imageSpan, datas1.get(i).intValue(), datas1.get(i).intValue()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }

        List<Integer> datas2 = getIndex(text, "2");
        if(datas2 != null && datas2.size() > 0){
            for (int i = 0; i < datas2.size(); i++){
                Drawable drawable = context.getResources().getDrawable(R.mipmap.qiye_vip2);
                drawable.setBounds(0, 0, 42, 42);
                ImageSpan imageSpan = new ImageSpan(drawable);
                spannableString.setSpan(imageSpan, datas2.get(i).intValue(), datas2.get(i).intValue()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }

        List<Integer> datas3 = getIndex(text, "3");
        if(datas3 != null && datas3.size() > 0){
            for (int i = 0; i < datas3.size(); i++){
                Drawable drawable = context.getResources().getDrawable(R.mipmap.qiye_vip3);
                drawable.setBounds(0, 0, 42, 42);
                ImageSpan imageSpan = new ImageSpan(drawable);
                spannableString.setSpan(imageSpan, datas3.get(i).intValue(), datas3.get(i).intValue()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }

        return spannableString;
    }

//    private static List<Integer> getIndexList(String text) {
//        List<Integer> datas = new ArrayList<>();
//        datas.addAll(getIndex(text, "1"));
//        datas.addAll(getIndex(text, "2"));
//        datas.addAll(getIndex(text, "3"));
//        return datas;
//    }

    /**
     * 获取strings字符串中所有str字符所在的下标
     * @param strings 母字符串
     * @param str 子字符串
     * @return 字符串在母字符串中下标集合，如果母字符串中不包含子字符串，集合长度为零
     */
    public static List<Integer> getIndex(String strings, String str){
        List<Integer> list=new ArrayList<>();
        int flag=0;
        while (strings.indexOf(str)!=-1){
            //截取包含自身在内的前边部分
            String aa= strings.substring(0,strings.indexOf(str)+str.length());
            flag=flag+aa.length();
            list.add(flag-str.length());
            strings=strings.substring(strings.indexOf(str)+str.length());
        }
        return list;
    }
}
