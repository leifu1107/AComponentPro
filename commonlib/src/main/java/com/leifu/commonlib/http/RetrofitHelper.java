package com.leifu.commonlib.http;

import com.leifu.commonlib.base.BaseBean;

import io.reactivex.Flowable;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:14
 * 描述:
 */

public class RetrofitHelper {
    private Apis api;

    public RetrofitHelper(Apis api) {
        this.api = api;
    }


    public Flowable<BaseBean> getDatas() {
        return api.getDatas();
    }


}
