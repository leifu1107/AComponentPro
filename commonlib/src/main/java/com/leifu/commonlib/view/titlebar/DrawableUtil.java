package com.leifu.commonlib.view.titlebar;

import android.graphics.drawable.Drawable;

/**
 * 创建人:雷富
 * 创建时间:2019/2/27 12:56
 * 描述:
 */
public class DrawableUtil {

    /**
     * 设置drawable宽高
     *
     * @param drawable
     * @param width
     * @param height
     */
    public static void setDrawableWidthHeight(Drawable drawable, int width, int height) {
        try {
            if (drawable != null) {
                drawable.setBounds(0, 0,
                        width >= 0 ? width : drawable.getIntrinsicWidth(),
                        height >= 0 ? height : drawable.getIntrinsicHeight());
            }
        } catch (Exception e) {
        }
    }

    /**
     * 复制当前drawable
     *
     * @param drawable
     * @return
     */
    public static Drawable getNewDrawable(Drawable drawable) {
        if (drawable == null) {
            return drawable;
        }
        return drawable.mutate();
    }

}
