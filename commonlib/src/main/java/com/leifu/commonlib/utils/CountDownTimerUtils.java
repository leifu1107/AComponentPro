package com.leifu.commonlib.utils;

/**
 * 倒计时
 */

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;

    /**
     * @param textView          The TextView
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receiver
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }

    public CountDownTimerUtils(TextView textView) {//60s倒计时
        super(60000, 1000);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        long time = millisUntilFinished / 1000;
        if (time < 10) {
            mTextView.setText(millisUntilFinished / 1000 + "s");
        } else {
            mTextView.setText(millisUntilFinished / 1000 + "s");  //设置倒计时时间
        }
        mTextView.setTextColor(Color.parseColor("#999999"));

    }

    @Override
    public void onFinish() {
        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setTextColor(Color.parseColor("#6D9DFF"));
    }
}