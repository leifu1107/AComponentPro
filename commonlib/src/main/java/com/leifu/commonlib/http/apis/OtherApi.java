package com.leifu.commonlib.http.apis;


import com.leifu.commonlib.base.BaseBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 17:11
 * 描述:
 */

public interface OtherApi {
    String HOST = "http://125.124.1.6:9602/";//正式环境
//    String HOST = "https://news-at.zhihu.com/";//正式环境

    //https://news-at.zhihu.com/api/4/news/latest
    @GET("api/4/news/latest")
    Flowable<BaseBean> getDatas();

    /**
     * 登陆
     */
    @POST("user/login")
    Flowable<BaseBean> loginRequest(@Body Map<String, Object> map);
}
