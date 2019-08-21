package com.example.baselibrary.zh.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class ToastUtils {
    public static Toast mToast = null;

    public static void showToast(Context mContext, String message) {
        if (!TextUtils.isEmpty(message)) {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(message);
            }
            mToast.show();
        }
    }
}
