package com.leifu.commonlib.di.module;


import com.leifu.commonlib.BuildConfig;
import com.leifu.commonlib.Constants;
import com.leifu.commonlib.http.MyApis;
import com.leifu.commonlib.http.RetrofitHelper;
import com.leifu.commonlib.utils.Logger;
import com.leifu.commonlib.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 17:05
 * 描述:
 */
@Module
public class HttpModule {

    private String authorization;

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    Retrofit provideZhiHuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, MyApis.HOST);
    }

    @Singleton
    @Provides
    MyApis provideZhiHuService(Retrofit retrofit) {
        return retrofit.create(MyApis.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton//可以不用单例
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {

        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtils.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);

                if (SystemUtils.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //--------------------- 设置统一的请求头部参数---------------------

//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                       .addHeader("headTest", SpUtils.getString("test","test"))
//                        .addHeader("Accept-Version", "1.0")
//                        .build();
//                return chain.proceed(request);
//            }
//        };
//
//        builder.addInterceptor(apikey);
        //-----------------日志拦截-------------------------
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Logger.e("OkHttp: HttpLoggingInterceptor----- " + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        //----------------设置缓存--------------------------

        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        //----------------https新增的--------------------------
//        String hosts[] = {"api.51geai.com"};
//        builder.hostnameVerifier(HttpsUtils.getHostnameVerifier(hosts));
//        builder.sslSocketFactory(HttpsUtils.createSSLSocketFactory(BaseApplication.getInstance().getApplicationContext()));
        return builder.build();
    }

    @Singleton
    @Provides
    RetrofitHelper provideRetrofitHelper(MyApis api) {
        return new RetrofitHelper(api);
    }
}
