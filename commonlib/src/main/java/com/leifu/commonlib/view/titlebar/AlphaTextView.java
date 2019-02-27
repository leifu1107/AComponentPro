package com.leifu.commonlib.view.titlebar;


import android.content.Context;
import android.util.AttributeSet;

/**
 * @Author: AriesHoo on 2018/7/19 9:56
 * @E-Mail: AriesHoo@126.com
 * Function: 控制Alpha 按下效果
 * Description:
 */
public class AlphaTextView extends android.support.v7.widget.AppCompatTextView {

    private AlphaDelegate delegate;

    public AlphaTextView(Context context) {
        this(context, null);
    }

    public AlphaTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
