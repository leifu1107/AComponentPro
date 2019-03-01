package com.leifu.commonlib.http.exception;

/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */
public class ApiException extends Exception {

    private int code;
    private String error;//error":"DK2010015","


    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public ApiException(String errorDescription, String error) {
        super(errorDescription);
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
