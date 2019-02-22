package com.leifu.commonlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.leifu.commonlib.utils.ToastUtil;

import javax.inject.Inject;


/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述: MVP Fragment基类
 */
public abstract class MVPFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected T mPresenter;

//    protected FragmentComponent getFragmentComponent() {
//        return DaggerFragmentComponent.builder()
//                .appComponent(BaseApplication.getAppComponent())
//                .fragmentModule(getFragmentModule())
//                .build();
//    }

//    protected FragmentModule getFragmentModule() {
//        return new FragmentModule(this);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.shortShow(msg);
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading(String message) {
//        LoadingUtil.showWaitDialog(mActivity, message);
    }



    protected abstract void initInject();
}