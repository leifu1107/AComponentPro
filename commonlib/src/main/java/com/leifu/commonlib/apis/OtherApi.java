package com.leifu.commonlib.apis;


import com.leifu.commonlib.base.BaseBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 17:11
 * 描述:
 */

public interface OtherApi {
    String HOST = "https://news-at.zhihu.com/";//正式环境

    //https://news-at.zhihu.com/api/4/news/latest
    @GET("api/4/news/latest")
    Flowable<BaseBean> getDatas();

}
