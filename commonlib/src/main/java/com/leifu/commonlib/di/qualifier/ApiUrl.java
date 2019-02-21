package com.leifu.commonlib.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 创建人:雷富
 * 创建时间:2019/2/21 17:36
 * 描述:
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ApiUrl {

}
