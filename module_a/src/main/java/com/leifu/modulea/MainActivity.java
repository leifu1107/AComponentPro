package com.leifu.modulea;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.EventBean;
import com.leifu.commonlib.base.MVPActivity;
import com.leifu.commonlib.utils.Logger;
import com.leifu.commonlib.utils.ToastUtil;
import com.leifu.modulea.di.DaggerUtils;
import com.leifu.modulea.presenter.MainPresenter;
import com.leifu.modulea.presenter.contract.MainContract;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.A_MainActivity)
public class MainActivity extends MVPActivity<MainPresenter> implements MainContract.View {
    @BindView(R2.id.btnModuleA)
    TextView mBtnModuleA;


    @Override
    protected int getLayout() {
        return R.layout.a_activity_main;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getDatas();
        mBtnModuleA.setText("ARouteConstants.A_MainActivity");
    }

    @Override
    protected void initInject() {
        DaggerUtils.getActivityComponent(this).inject(this);
    }

    @Override
    public void showContent(BaseBean baseBean) {

    }


    @OnClick(R2.id.btnModuleA)
    public void onViewClicked() {
        ToastUtil.shortShow("库的module");
//        ArouterUtil.navigation(ARouteConstants.B_MainActivity);
        ARouter.getInstance().build(ARouteConstants.B_MainActivity).navigation();
    }

    @Override
    public void onEvent(EventBean eventBean) {
        super.onEvent(eventBean);
        Logger.e("aaa----"+eventBean.toString());
    }
}
