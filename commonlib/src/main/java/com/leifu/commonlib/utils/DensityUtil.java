package com.leifu.commonlib.utils;

/**
 * 创建人:雷富
 * 创建时间:2019/2/22 17:15
 * 描述:
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.leifu.commonlib.app.BaseApplication;

/**
 * 屏幕工具类，涉及到屏幕宽度、高度、密度比、(像素、dp、sp)之间的转换等。
 */
public class DensityUtil {

    /**
     * 获取屏幕宽度，单位为px
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度，单位为px
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取系统dp尺寸密度值
     */
    public static float getDensity() {
        return getDisplayMetrics().density;
    }

    /**
     * 获取系统字体sp密度值
     */
    public static float getScaledDensity() {
        return getDisplayMetrics().scaledDensity;
    }

    /**
     * dip转换为px大小
     *
     * @param dpValue dp值
     * @return 转换后的px值
     */
    public static int dp2px(int dpValue) {
        return (int) (dpValue * getDensity() + 0.5f);
    }

    /**
     * px转换为dp值
     *
     * @param pxValue px值
     * @return 转换后的dp值
     */
    public static int px2dp(int pxValue) {
        return (int) (pxValue / getDensity() + 0.5f);
    }

    /**
     * sp转换为px
     *
     * @param spValue sp值
     * @return 转换后的px值
     */
    public static int sp2px(int spValue) {
        return (int) (spValue * getScaledDensity() + 0.5f);
    }

    /**
     * px转换为sp
     *
     * @param pxValue px值
     * @return 转换后的sp值
     */
    public static int px2sp(int pxValue) {
        return (int) (pxValue / getScaledDensity() + 0.5f);
    }

    /**
     * 获得状态栏的高度
     *
     * @return
     */
    public static int getStatusHeight() {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = BaseApplication.getInstance().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap bmp = decorView.getDrawingCache();
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bitmap;
        bitmap = Bitmap.createBitmap(bmp, 0, 0, width, height);
        decorView.destroyDrawingCache();
        return bitmap;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap bmp = decorView.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusHeight = frame.top;
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bitmap;
        bitmap = Bitmap.createBitmap(bmp, 0, statusHeight, width, height - statusHeight);
        decorView.destroyDrawingCache();
        return bitmap;
    }

    /**
     * 获取DisplayMetrics对象
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics() {
        WindowManager manager = (WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取view的宽和高
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
}
