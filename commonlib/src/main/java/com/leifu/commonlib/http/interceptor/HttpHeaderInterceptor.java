package com.leifu.commonlib.http.interceptor;

import android.text.TextUtils;

import com.leifu.commonlib.utils.Logger;
import com.leifu.commonlib.utils.encode.DesUtils;
import com.leifu.commonlib.utils.encode.SignUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class HttpHeaderInterceptor implements Interceptor {
    private static final String TAG = "tag";
    private String mobSysInfo, appVersion, userCode, mobDevId="";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String sign = "";
        String token = "";
        String timestamp = System.currentTimeMillis() + "";
        if (TextUtils.isEmpty(token)) {
            sign = SignUtil.sign("SHA256", timestamp);
        } else {
            try {
                sign = SignUtil.sign("SHA256", DesUtils.decrypt(token) + timestamp);
            } catch (Exception e) {
                Logger.e("sign-Exception");
            }

        }
        Request.Builder build = request.newBuilder()
                .addHeader("sign", sign)
                .addHeader("req_time", timestamp)
                .addHeader("mob_id", "123456")
                .addHeader("app_version", "android_" + 1.0)
                .addHeader("mob_info", "111");
        if (!TextUtils.isEmpty(userCode)) {
            build.addHeader("user_code", userCode);
        }
        return chain.proceed(build.build());
    }


}
