package com.leifu.commonlib.di.module;


import com.leifu.commonlib.app.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/13 16:19
 * 描述:
 */

@Module
public class AppModule {
    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplicationContext() {
        return application;
    }


}
