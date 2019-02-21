package com.leifu.commonlib.di.component;


import com.leifu.commonlib.app.BaseApplication;
import com.leifu.commonlib.di.module.AppModule;
import com.leifu.commonlib.di.module.HttpModule;
import com.leifu.commonlib.http.Apis;
import com.leifu.commonlib.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 16:06
 * 描述:
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    BaseApplication getContext();  // 提供App的Context

    RetrofitHelper getRetrofitHelper();

    Apis getApis();
}
