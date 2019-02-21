package com.leifu.modulea.di.module;

import android.app.Activity;

import com.leifu.commonlib.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 *创建人: 雷富
 * 创建时间: 2018/3/13 16:19
 * 描述:
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
