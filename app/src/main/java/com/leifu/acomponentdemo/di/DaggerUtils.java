package com.leifu.acomponentdemo.di;

import android.app.Activity;

import com.leifu.acomponentdemo.App;
import com.leifu.acomponentdemo.di.component.ActivityComponent;
import com.leifu.acomponentdemo.di.component.DaggerActivityComponent;
import com.leifu.commonlib.di.module.ActivityModule;


/**
 * 创建人:雷富
 * 创建时间:2019/2/21 16:43
 * 描述:
 */
public class DaggerUtils {
    public static ActivityComponent getActivityComponent(Activity activity) {
        ActivityComponent mActivityComponent =   DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(activity))
                .build();
        return mActivityComponent;
    }
}
