package com.leifu.acomponentdemo;


import com.alibaba.android.arouter.launcher.ARouter;
import com.leifu.commonlib.app.BaseApplication;

/**
 * 创建人:雷富
 * 创建时间:2019/2/19 17:32
 * 描述:
 */
public class App extends BaseApplication {

//    private static AppComponent appComponent;

    @Override
    public void init() {
        super.init();
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

//    public static AppComponent getAppComponent() {
//        if (appComponent == null) {
//            appComponent = DaggerAppComponent.builder()
//                    .appModule(new AppModule(BaseApplication.getInstance()))
//                    .httpModule(new HttpModule())
//                    .build();
//        }
//        return appComponent;
//    }
}
