package com.leifu.commonlib.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.leifu.commonlib.app.BaseApplication;


/**
 * Created by zitan on 2018/6/8.
 */

public class ChannelUtils {

    public static String getChannel() {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = BaseApplication.getInstance().getPackageManager().
                    getApplicationInfo(BaseApplication.getInstance().getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (applicationInfo != null) {
            if (applicationInfo.metaData != null) {
                return String.valueOf(applicationInfo.metaData.get("UMENG_CHANNEL_VALUE"));
            }
        }
        return "";
    }


}
