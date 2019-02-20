package com.leifu.acomponentdemo;

import com.leifu.acomponentdemo.di.component.DaggerActivityComponent;
import com.leifu.acomponentdemo.di.module.ActivityModule;
import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.BaseMVPActivity;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View {


    @Override
    public void showContent(BaseBean baseBean) {

    }

    @Override
    protected void initInject() {
        DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getDatas();
    }

}
