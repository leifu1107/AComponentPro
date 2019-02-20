package com.leifu.acomponentdemo.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/13 16:19
 * 描述:
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
