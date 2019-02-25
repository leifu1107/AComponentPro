package com.leifu.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 创建人:雷富
 * 创建时间:2019/2/25 10:03
 * 描述:
 */
public class ViewUtil {
    /**
     * 设置hint大小
     *
     * @param size
     * @param v
     * @param charSequence 字符串内容
     */
    public static void setViewHintSize(Context context, int size, TextView v, CharSequence charSequence) {
        SpannableString ss = new SpannableString(charSequence);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置hint
        v.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }

    /**
     * 获取view的宽和高
     *
     * @param contentView
     * @return
     */
    public static int[] getViewWidthAndHeight(View contentView) {
        int[] result = new int[2];
        if (contentView.getMeasuredHeight() == 0 || contentView.getMeasuredWidth() == 0) {
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            // 计算contentView的高宽
            int windowHeight = contentView.getMeasuredHeight();
            int windowWidth = contentView.getMeasuredWidth();
            result[0] = windowWidth;
            result[1] = windowHeight;
        }
        return result;
    }

    /**
     * 设置控件margin单位 px
     *
     * @param v 控件
     * @param l 左
     * @param t 上
     * @param r 右
     * @param b 下
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 全屏,并且沉侵式状态栏
     *
     * @param activity
     */
    public static void statusInScreen(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
