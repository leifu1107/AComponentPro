package com.leifu.commonlib.permission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionCanceled {

    int requestCode() default 0;
}
