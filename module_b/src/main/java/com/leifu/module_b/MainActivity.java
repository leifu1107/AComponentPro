package com.leifu.module_b;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseActivity;
import com.leifu.commonlib.base.EventBean;

import org.greenrobot.eventbus.EventBus;

@Route(path = ARouteConstants.B_MainActivity)
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.b_activity_main;
    }

    @Override
    protected void initEventAndData() {
        EventBus.getDefault().post(new EventBean(1,"bbbbbbb"));
    }
}
