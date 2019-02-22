package com.leifu.commonlib.base;


import com.leifu.commonlib.utils.ToastUtil;
import com.leifu.commonlib.view.dialog.LoadingUtil;

import javax.inject.Inject;


/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:MVP activity基类
 */
public abstract class MVPActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;
//组件化需要单独每个组件配置BaseActivity减少代码;di文件下的component和module也需要单独配置
//    protected ActivityComponent getActivityComponent() {
//        return DaggerActivityComponent.builder()
//                .appComponent(BaseApplication.getAppComponent())
//                .activityModule(new ActivityModule(this))
//                .build();
//    }


    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
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
        LoadingUtil.showLoading(mActivity, message);
    }


    protected abstract void initInject();
}