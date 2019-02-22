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
    private View viewLoading;

    @Override
    protected void initEventAndData() {
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
}
