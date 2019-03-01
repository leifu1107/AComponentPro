package com.leifu.commonlib.utils.encode;

import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2015/7/2.
 */
public class SignUtil {
    private static final String TAG = "SignUtil";

    /**
     * 用指定方式加密
     *
     * @param method      加密方式
     * @param originalStr 要加密的字符串
     * @return 加密后字符串
     */
    public static String sign(String method, String originalStr) {
        if (TextUtils.isEmpty(method)) {
            return null;
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(correctMethod(method));
            md.reset();
            md.update(originalStr.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("不存在指定加密方法：" + method +e);
            return null;
        } catch (UnsupportedEncodingException e1) {
            //该异常不处理
            e1.printStackTrace();
            return null;
        }

        return String.valueOf(Hex.encodeHex(md.digest()));
    }

    /**S
     * 修正签名方法
     *
     * @param method
     * @return
     */
    public static String correctMethod(String method) {
        if (method.equals("SHA256")) {
            method = "SHA-256";
        }
        return method.toUpperCase();
    }

    public static void test() {
        Long timestamp =System.currentTimeMillis() / 1000;
        String token ="asdjkjkxbnZasdzbxcn==";
        //传送sign
        String respon = SignUtil.sign("SHA256", token+timestamp);
        Log.e(TAG,"传送参数sign："+respon);
        //传送sign
        if(respon.equals(SignUtil.sign("SHA256", token+timestamp))){
            Log.e(TAG,"验签正确");
            return;
        }
        Log.e(TAG,"验签失败，请求不合法");
    }

}
