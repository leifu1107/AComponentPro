package com.leifu.commonlib;

import android.Manifest;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.leifu.commonlib.base.BaseActivity;
import com.leifu.commonlib.permission.annotation.NeedPermission;
import com.leifu.commonlib.permission.annotation.PermissionCanceled;
import com.leifu.commonlib.permission.annotation.PermissionDenied;
import com.leifu.commonlib.permission.bean.CancelBean;
import com.leifu.commonlib.permission.bean.DenyBean;
import com.leifu.commonlib.utils.ToastUtil;

/**
 * 创建人:雷富
 * 创建时间:2019/2/26 14:39
 * 描述:
 */
@Route(path = ARouteConstants.Test_TestActivity)
public class TestActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_request_permission;
    }

    @NeedPermission(value = {Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, requestCode = 10)
    @Override
    protected void initEventAndData() {
        ToastUtil.shortShow("跳过来了");
        setTitleText("中心","右边",0x0000ff00);
    }

    @PermissionDenied
    public void onPermissionDenied(DenyBean bean) {
        ToastUtil.shortShow("权限不同意");
    }

    @PermissionCanceled
    public void onPermissionCanceled(CancelBean bean) {
        ToastUtil.shortShow("权限取消");
    }

}
