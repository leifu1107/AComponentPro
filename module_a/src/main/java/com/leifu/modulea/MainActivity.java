package com.leifu.modulea;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.MVPActivity;
import com.leifu.modulea.di.DaggerUtils;
import com.leifu.modulea.presenter.MainPresenter;
import com.leifu.modulea.presenter.contract.MainContract;

@Route(path = ARouteConstants.A_MainActivity)
public class MainActivity extends MVPActivity<MainPresenter> implements MainContract.View {


    @Override
    protected int getLayout() {
        return R.layout.a_activity_main;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getDatas();
    }

    @Override
    protected void initInject() {
        DaggerUtils.getActivityComponent(this).inject(this);
    }

    @Override
    public void showContent(BaseBean baseBean) {

    }
}
