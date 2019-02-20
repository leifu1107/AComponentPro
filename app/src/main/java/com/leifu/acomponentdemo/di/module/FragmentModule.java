package com.leifu.acomponentdemo.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.leifu.acomponentdemo.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
