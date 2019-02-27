package com.leifu.acomponentdemo.ui.activity;

import android.os.Handler;

import com.leifu.acomponentdemo.R;
import com.leifu.commonlib.ARouteConstants;
import com.leifu.commonlib.base.BaseActivity;
import com.leifu.commonlib.utils.ArouterUtil;


/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 14:12
 * 描述:闪屏页
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        goNextActivity();
//        ArouterUtil.navigation(ARouteConstants.Main_MainActivity);
    }


    private void goNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                boolean isFirst = SpUtils.setBoolean(Constants.sp_guide_first, true);
//                if (isFirst) {
//                    //跳转页面
//                    mStartActivity(GuideActivty.class);
//                } else {
//                    mStartActivity(MainActivity.class);
//                }
//                mStartActivity(MainActivity.class);
//                finish();
                ArouterUtil.navigation(ARouteConstants.Main_MainActivity);
                finish();
            }
        }, 1000);
    }

}
