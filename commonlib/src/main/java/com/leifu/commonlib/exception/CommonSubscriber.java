package com.leifu.commonlib.exception;


import com.leifu.commonlib.base.BaseView;
import com.leifu.commonlib.utils.Logger;
import com.leifu.commonlib.utils.SystemUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String message = "加载中";
    private boolean showLoading = true;

    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, boolean showLoading) {
        this.mView = view;
        this.showLoading = showLoading;
    }

    protected CommonSubscriber(BaseView view, String message) {
        this.mView = view;
        this.message = message;
    }

    //自己加的,防止出现问题,测试
    @Override
    protected void onStart() {
        super.onStart();
        if (!SystemUtils.isNetworkConnected()) {
//            mView.showErrorMsg("当前网络不可用，请检查网络情况");
            onError(new NetworkException("当前网络不可用\n请检查网络设置"));
            if (!isDisposed()) {
                dispose();
            }
            return;
        }
        if (showLoading) {
            mView.stateLoading(message);
        }

    }

    @Override
    public void onComplete() {
        Logger.e("OkHttp" + "************************onComplete");
        if (showLoading) {
//            ProgressDialogUtil.stopWaitDialog();
        }

    }

    @Override
    public void onError(Throwable e) {
        if (showLoading) {
//            ProgressDialogUtil.stopWaitDialog();
        }
        Logger.e("OkHttp:_______________presenter所有的错误响应e.toString()_______________  " + e.toString());
        if (mView == null)
            return;
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            if (apiException.getError().equals("ERROR_AUTHORITY_TOKEN_NOT_EXIST") || apiException.getError().equals("ERROR_AUTHORITY_NEED_AUTH") || (apiException.getError()
                    .equals("ERROR_AUTHORITY_PRINCIPAL_CHECK") || "无效的请求".equals(e.getMessage()))) {
                mView.showErrorMsg("请重新登录");
//                mView.goLogin();
                return;
            }
            mView.showErrorMsg(e.getMessage());
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            if (httpException.code() == 403) {
                mView.showErrorMsg("请重新登录");
//                mView.goLogin();
                return;
            } else if (httpException.code() == 500) {
                mView.showErrorMsg("服务不可用");
                return;
            }
            mView.showErrorMsg(e.getMessage());//403
        } else if (e instanceof NetworkException) {
            mView.showErrorMsg(e.getMessage());//
        } else if (e instanceof SocketTimeoutException) {
            mView.showErrorMsg("连接超时,请稍后再试");
        } else if (e instanceof ConnectException) {
            mView.showErrorMsg("服务器连接失败");
        } else {
            mView.showErrorMsg("未知错误");
//            CrashReport.postCatchedException(e);
        }
    }
}
