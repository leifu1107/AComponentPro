package com.leifu.commonlib.base;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述: Presenter基类
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
