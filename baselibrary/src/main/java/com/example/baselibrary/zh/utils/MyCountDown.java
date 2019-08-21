package com.example.baselibrary.zh.utils;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/31 0031.
 * 倒计时
 */
public class MyCountDown extends CountDownTimer {
    private TextView textView;
    private String finishStr, countDownStr;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDown(long millisInFuture, long countDownInterval, TextView textView, String finishStr, String countDownStr) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.finishStr = finishStr;
        this.countDownStr = countDownStr;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        String time = millisUntilFinished / 1000 + countDownStr;
        if (!TextUtils.isEmpty(time)) {
            textView.setText(time);
            textView.setClickable(false);
        }
    }

    @Override
    public void onFinish() {
        textView.setText(finishStr);
        textView.setClickable(true);
    }
}
