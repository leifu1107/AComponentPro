package com.leifu.commonlib.permission.support;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.leifu.commonlib.permission.interf.ISetting;



/**
 * 创建人:雷富
 * 创建时间:2018/7/30 17:38
 * 描述:
 */

public class Default implements ISetting {

    private Context context;

    public Default(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return intent;
    }
}
