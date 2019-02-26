package com.leifu.commonlib.permission.bean;

import java.util.List;


/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */

public class DenyBean {

    private int requestCode;
    private List<String> denyList;

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public List<String> getDenyList() {
        return denyList;
    }

    public void setDenyList(List<String> denyList) {
        this.denyList = denyList;
    }
}
