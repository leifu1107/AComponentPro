package com.leifu.commonlib.base;

import java.io.Serializable;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/27 16:03
 * 描述:
 */

public class BaseBean implements Serializable {


    /**
     * error : ERROR_AUTHORITY_DENIED
     * errorDescription : 无权访问
     * success : false
     */

    private String error;
    private String errorDescription;
    private boolean success;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
