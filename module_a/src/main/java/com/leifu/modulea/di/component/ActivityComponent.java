package com.leifu.modulea.di.component;

import android.app.Activity;

import com.leifu.commonlib.di.component.AppComponent;
import com.leifu.commonlib.di.scope.ActivityScope;
import com.leifu.modulea.MainActivity;
import com.leifu.modulea.di.module.ActivityModule;

import dagger.Component;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 16:08
 * 描述:
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity activity);

}

