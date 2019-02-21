package com.leifu.modulea.di;

import android.app.Activity;

import com.leifu.commonlib.app.BaseApplication;
import com.leifu.modulea.di.component.ActivityComponent;
import com.leifu.modulea.di.component.DaggerActivityComponent;
import com.leifu.modulea.di.module.ActivityModule;

/**
 * 创建人:雷富
 * 创建时间:2019/2/21 16:43
 * 描述:
 */
public class DaggerUtils {
    public static ActivityComponent getActivityComponent(Activity activity) {
        ActivityComponent mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(new ActivityModule(activity))
                .build();
        return mActivityComponent;
    }
}
