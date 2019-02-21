package com.leifu.acomponentdemo;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.leifu.acomponentdemo.di.DaggerUtils;
import com.leifu.acomponentdemo.presenter.MainPresenter;
import com.leifu.acomponentdemo.presenter.contract.MainContract;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.MVPActivity;

public class MainActivity extends MVPActivity<MainPresenter> implements MainContract.View {
    @Override
    public void showContent(BaseBean baseBean) {

    }

    @Override
    protected void initInject() {
        DaggerUtils.getActivityComponent(this).inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

        mPresenter.getDatas();
        findViewById(R.id.btnGoModuleA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.A_MainActivity).navigation();
            }
        });
    }

}
