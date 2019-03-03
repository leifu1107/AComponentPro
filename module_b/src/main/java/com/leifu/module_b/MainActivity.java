package com.leifu.module_b;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseActivity;
import com.leifu.commonlib.base.EventBean;

import org.greenrobot.eventbus.EventBus;

@Route(path = ARouteConstants.B_MainActivity)
public class MainActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.moduleb_activity_main;
    }

    @Override
    public void initEventAndData() {
        setTitleText("b","",0);

        EventBus.getDefault().post(new EventBean(1,"bbbbbbb"));
    }
}
