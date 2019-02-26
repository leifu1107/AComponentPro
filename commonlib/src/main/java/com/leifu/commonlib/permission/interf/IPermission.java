package com.leifu.commonlib.permission.interf;

import java.util.List;


/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */

public interface IPermission {

    //同意权限
    void PermissionGranted();

    //拒绝权限并且选中不再提示
    void PermissionDenied(int requestCode, List<String> denyList);

    //取消权限
    void PermissionCanceled(int requestCode);
}
