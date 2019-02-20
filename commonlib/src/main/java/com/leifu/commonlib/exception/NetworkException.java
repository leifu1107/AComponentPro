package com.leifu.commonlib.exception;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */
public class NetworkException extends Exception {

    private String error;


    public NetworkException(String msg) {
        super(msg);
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
