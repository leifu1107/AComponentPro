package com.leifu.acomponentdemo.di.component;

import android.app.Activity;

import com.leifu.commonlib.di.module.FragmentModule;
import com.leifu.commonlib.di.component.AppComponent;
import com.leifu.commonlib.di.scope.FragmentScope;

import dagger.Component;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

//    void inject(MyFragment fragment);

}
