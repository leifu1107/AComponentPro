package com.leifu.commonlib.base;


import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建人:雷富
 *  创建时间:2018/7/30 17:38
 *  描述: 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> handleResult1() {   //compose判断结果
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<T, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(T bean) {
//                        Logger.e("OkHttp------sonUtil.toJson--------" + JsonUtil.toJson(bean));
                        return createData(bean);
//                        BaseBean baseBean = (BaseBean) bean;
//                        if (baseBean.getSuccess()) {
//                            return createData(bean);
//                        } else {
//                            return Flowable.error(new ApiException(baseBean.getErrorDescription(),baseBean.getError()));
//                        }
                    }
                });
            }
        };
    }


    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
//    public static <T> FlowableTransformer<BaseBean<T>, T> handleResult() {   //compose判断结果
//
//        return new FlowableTransformer<BaseBean<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<BaseBean<T>> httpResponseFlowable) {
//
//
//                return httpResponseFlowable.flatMap(new Function<BaseBean<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(BaseBean<T> baseBean) {
//                        Logger.e("a---" + JsonUtil.toJson(baseBean));
//                        if (baseBean.getSuccess()) {
//                            return createData(baseBean.getResults());
//                        } else {
//                            return Flowable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }

//    public static <T> FlowableTransformer<GankHttpResponse<T>, T> handleResult() {   //compose判断结果
//
//        return new FlowableTransformer<GankHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<GankHttpResponse<T>> httpResponseFlowable) {
//
//
//                return httpResponseFlowable.flatMap(new Function<GankHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(GankHttpResponse<T> tGankHttpResponse) {
//                        Logger.e("a---" + JsonUtil.toJson(tGankHttpResponse));
//                        if (!tGankHttpResponse.getError()) {
//                            return createData(tGankHttpResponse.getResults());
//                        } else {
//                            return Flowable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }
    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
//    public static <T> FlowableTransformer<T, T> handleResult1() {   //compose判断结果
//        return new FlowableTransformer<T, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<T> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<T, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(T tGankHttpResponse) {
//                        Logger.e("a---" + JsonUtil.toJson(tGankHttpResponse));
//                        BaseBean baseBean = (BaseBean) tGankHttpResponse;
//                        if (baseBean.getState()==1) {
//                            return createData(tGankHttpResponse);
//                        } else {
//                            return Flowable.error(new ApiException("请重新登录"));
//                        }
//                    }
//                });
//            }
//        };
//    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
