package com.leifu.acomponentdemo;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.leifu.acomponentdemo.di.DaggerUtils;
import com.leifu.acomponentdemo.presenter.MainPresenter;
import com.leifu.acomponentdemo.presenter.contract.MainContract;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseBean;
import com.leifu.commonlib.base.EventBean;
import com.leifu.commonlib.base.RootActivity;
import com.leifu.commonlib.utils.Logger;
import com.leifu.commonlib.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.Main_MainActivity)
public class MainActivity extends RootActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.btnGoModuleA)
    TextView mBtnGoModuleA;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void initEventAndData() {
        setTitleText("标题", "右边", R.color.colorPrimary);
//        stateMain();
        stateError();
    }

    @Override
    public void presenterData() {
        mPresenter.getData();
        ToastUtil.shortShow("chongxin");
    }

    @OnClick(R.id.btnGoModuleA)
    public void onViewClicked() {
        ARouter.getInstance().build(ARouteConstants.A_MainActivity).navigation();
    }

    @Override
    public void onEvent(EventBean eventBean) {
        super.onEvent(eventBean);
        Logger.e("main----" + eventBean.toString());
    }

    @Override
    public void showContent(BaseBean baseBean) {
        Logger.e("main----" + baseBean.toString());

        stateMain();
    }

    @Override
    public void initInject() {
        DaggerUtils.getActivityComponent(this).inject(this);

    }


}
