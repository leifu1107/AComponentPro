package com.leifu.commonlib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leifu.commonlib.R;
import com.leifu.commonlib.utils.ActivityManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述: 无MVP的activity基类
 */

public abstract class BaseActivity extends SupportActivity {

    public Context mContext;
    private Unbinder mUnBinder;

    public TextView mCenterTitle;
    public LinearLayout mTitleLayout;
    public TextView mRightText;
    public ImageView mBtnBack;
    //    public ImmersionBar mImmersionBar;
    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this.getApplicationContext();
        mActivity = this;
        EventBus.getDefault().register(this);
        onViewCreated();
        ActivityManager.getInstance().addActivity(this);
        initEventAndData();
    }


    protected void onViewCreated() {
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    /**
     * 设置布局标题头
     *
     * @param centerTitle 中间标题
     * @param rightText   布局最右边文字
     * @param bgColor     整个头布局背景颜色
     */
    public void setTitleText(String centerTitle, String rightText, int bgColor) {
        mCenterTitle = (TextView) findViewById(R.id.centerTitle);
        mTitleLayout = (LinearLayout) findViewById(R.id.titleLayout);
        mRightText = (TextView) findViewById(R.id.rightText);
        mBtnBack = (ImageView) findViewById(R.id.btnBack);
        mCenterTitle.setText(centerTitle);
        mRightText.setText(rightText);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleLayout.setBackgroundResource(bgColor);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
        EventBus.getDefault().unregister(this);
        mUnBinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBean eventBean) {
    }

    /**
     * 粘性传值,如前面的activity传值到后面的activity
     *
     * @param eventBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(EventBean eventBean) {

    }


    /**
     * 按到键盘外隐藏键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
