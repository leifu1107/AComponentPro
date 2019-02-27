package com.leifu.commonlib.view.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 创建人:雷富
 * 创建时间:2019/2/27 12:54
 * 描述:
 */
public class AlphaImageView extends android.support.v7.widget.AppCompatImageView {

    private AlphaDelegate delegate;

    public AlphaImageView(Context context) {
        this(context, null);
    }

    public AlphaImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new AlphaDelegate(this);
    }

    public AlphaDelegate getDelegate() {
        return delegate;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        delegate.getAlphaViewHelper().onPressedChanged(this, pressed);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        delegate.getAlphaViewHelper().onEnabledChanged(this, enabled);
    }
}
