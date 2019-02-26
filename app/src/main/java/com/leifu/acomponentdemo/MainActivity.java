package com.leifu.acomponentdemo;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.leifu.acomponentdemo.di.DaggerUtils;
import com.leifu.acomponentdemo.presenter.MainPresenter;
import com.leifu.acomponentdemo.presenter.contract.MainContract;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.RootActivity;

import butterknife.BindView;
import butterknife.OnClick;
@Route(path = ARouteConstants.Main_MainActivity)
public class MainActivity extends RootActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.btnGoModuleA)
    TextView mBtnGoModuleA;

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
        super.initEventAndData();
        mPresenter.getDatas();
    }


    @OnClick(R.id.btnGoModuleA)
    public void onViewClicked() {
        ARouter.getInstance().build(ARouteConstants.Test_TestActivity).navigation();
    }
}
