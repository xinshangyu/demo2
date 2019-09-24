package com.example.administrator.demo.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.administrator.demo.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 文件名：SmsTimeUtils
 */
public class SmsTimeUtils {

    /*倒计时时长  单位：秒*/
    private final static int COUNT = 60;
    /*当前做*/
    private static int CURR_COUNT = 0;
    /*发送验证码*/
    public final static int SETTING_FINANCE_ACCOUNT_TIME = 1;
    public final static int SETTING_FINANCE_ACCOUNT_TIME2 = 2;
    public final static int SETTING_FINANCE_ACCOUNT_TIME3 = 3;
    public final static int WITHDRAW_CASH = 4;

    private static long SETTING_FINANCE_ACCOUNT_TIME_END = 0;
    private static long SETTING_FINANCE_ACCOUNT_TIME_END2 = 0;
    private static long SETTING_FINANCE_ACCOUNT_TIME_END3 = 0;
    private static long WITHDRAW_CASH_END = 0;

    private static Timer countdownTimer;
    private static TextView tvSendCode;
    private static Context mContext;

    /**
     * 检查是否超过60秒
     * 给当前要从多少开始倒数赋值
     *
     * @param first true 表示第一次   false不是
     * @return 是否需要调用startCountdown(TextView textView)，主要用于判断在重新打开页，需不需要继续倒计时
     */
    public static boolean check(int type, boolean first) {
        long data = System.currentTimeMillis();
        long time = 0;
        switch (type) {
            case SETTING_FINANCE_ACCOUNT_TIME:
                time = SETTING_FINANCE_ACCOUNT_TIME_END;
                break;
            case SETTING_FINANCE_ACCOUNT_TIME2:
                time = SETTING_FINANCE_ACCOUNT_TIME_END2;
                break;
            case SETTING_FINANCE_ACCOUNT_TIME3:
                time = SETTING_FINANCE_ACCOUNT_TIME_END3;
                break;
            case WITHDRAW_CASH:
                time = WITHDRAW_CASH_END;
                break;
        }
        if (data > time) {
            /*主要是区别于是否是第一次进入。第一次进入不需要赋值*/
            if (!first) {
                CURR_COUNT = COUNT;
                time = data + COUNT * 1000;
                switch (type) {
                    case SETTING_FINANCE_ACCOUNT_TIME:
                        SETTING_FINANCE_ACCOUNT_TIME_END = time;
                        break;
                    case SETTING_FINANCE_ACCOUNT_TIME2:
                        SETTING_FINANCE_ACCOUNT_TIME_END2 = time;
                        break;
                    case SETTING_FINANCE_ACCOUNT_TIME3:
                        SETTING_FINANCE_ACCOUNT_TIME_END3 = time;
                        break;
                    case WITHDRAW_CASH:
                        WITHDRAW_CASH_END = time;
                        break;
                }
            }
            return false;
        } else {
            int the_difference = ((int) (time - data)) / 1000;
            CURR_COUNT = the_difference;
            return true;
        }
    }

    /**
     * 开始倒计时
     *
     * @param textView 控制倒计时的view
     */
    public static void startCountdown(TextView textView, Context context) {
        tvSendCode = textView;
        mContext = context;
        if (countdownTimer == null) {
            countdownTimer = new Timer();
            countdownTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = CURR_COUNT--;
                    handler.sendMessage(msg);
                }
            }, 0, 1000);
        }
    }


    private static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (countdownTimer != null) {
                    countdownTimer.cancel();
                    countdownTimer = null;
                }
                tvSendCode.setText(mContext.getResources().getString(R.string.privacy_code));
                tvSendCode.setEnabled(true);
            } else {
                tvSendCode.setText(msg.what + mContext.getResources().getString(R.string.login_get_code_count));
                tvSendCode.setEnabled(false);
            }
            super.handleMessage(msg);
        }
    };
}
