package com.leifu.commonlib.base;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);


    //=======  State  =======
    void stateError();

    void stateLoading(String message);

//    void goLogin();
}
