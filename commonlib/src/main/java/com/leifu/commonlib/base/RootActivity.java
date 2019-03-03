package com.leifu.commonlib.base;

import android.view.View;
import android.view.ViewGroup;

import com.leifu.commonlib.R;

/**
 * 创建人:雷富
 * 创建时间:2019/2/22 13:15
 * 描述:
 */
public abstract class RootActivity<T extends BasePresenter> extends MVPActivity<T> {
    private View viewMain;
    private ViewGroup mParent;
    //    private View viewLoading;
    private View viewError;

    @Override
    public void findByIdMainView() {
        super.findByIdMainView();
        viewMain = findViewById(R.id.view_main);
        if (viewMain == null) {
            throw new IllegalStateException("The subclass of RootActivity must contain a View named 'view_main'.");
        }
        mParent = (ViewGroup) viewMain.getParent();
//        View.inflate(mContext, R.layout.custom_view_loading, mParent);
//        viewLoading = mParent.findViewById(R.id.view_loading);
//        viewLoading.setVisibility(View.VISIBLE);
        viewMain.setVisibility(View.GONE);
    }

    @Override
    public void initEventAndDataAfter() {
        super.initEventAndDataAfter();
        presenterData();//为了减少重新加载方法,把网络请求写到一起
    }

    public abstract void presenterData();

    private static final int STATE_MAIN = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_ERROR = 2;
    private int currentState = 1;
    private boolean isErrorViewAdded = false;

    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
//        viewLoading.setVisibility(View.VISIBLE);

    }

    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        if (!isErrorViewAdded) {
            isErrorViewAdded = true;
            View.inflate(mContext, R.layout.custom_view_error, mParent);
            viewError = mParent.findViewById(R.id.view_error);
            if (viewError == null) {
                throw new IllegalStateException("A View should be named 'view_error' in ErrorLayoutResource.");
            }
        }
        hideCurrentView();
        currentState = STATE_ERROR;
        viewError.setVisibility(View.VISIBLE);

        mParent.findViewById(R.id.reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterData();
                if (listener != null) {
                    listener.onReload();
                }
            }
        });
    }

    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
//                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                if (viewError != null) {
                    viewError.setVisibility(View.GONE);
                }
                break;
        }
    }

    private OnReloadListener listener;

    public void setOnReloadListener(OnReloadListener listener) {
        this.listener = listener;
    }

    public interface OnReloadListener {
        void onReload();
    }
}
