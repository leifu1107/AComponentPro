package com.leifu.commonlib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述: 无MVP的Fragment基类
 */

public abstract class BaseFragment extends SupportFragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initEventAndData();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void mStartActivity(Class<?> intentActivity) {
        Intent intent = new Intent(mContext, intentActivity);
        super.startActivity(intent);
    }

    public void mStartActivity(Class<?> intentActivity, Bundle bundle) {
        Intent intent = new Intent(mContext, intentActivity);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        super.startActivity(intent);
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
