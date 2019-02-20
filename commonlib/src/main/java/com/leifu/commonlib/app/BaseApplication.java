package com.leifu.commonlib.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


/**
 * 创建人: 雷富
 * 创建时间: 2018/3/19 15:33
 * 描述:
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
//    private static AppComponent appComponent;

//    //static 代码段可以防止内存泄露
//    static {
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @NonNull
//            @Override
//            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
//                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
//                layout.setPrimaryColorsId(R.color.white, R.color.light_gray);
//
//                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(20);
//            }
//        });
//    }

    @Override
    public void onCreate() {
        super.onCreate();

//        String channel = WalleChannelReader.getChannel(getApplication());
//        Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
//        Bugly.init(this, "4ba8c0d396", false);

        instance = this;
//        MoxieSDK.init(this);
//        try {
//            DiskLruCacheHelper.getInstance().init(getApplicationContext());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //---------------调试工具记得发布时候注释掉---------------
//        //---------------调试工具记得发布时候注释掉---------------
//        //---------------调试工具记得发布时候注释掉---------------
//        LeakCanary.install(this);
//        //初始化过度绘制检测
//        BlockCanary.install(getApplicationContext(), new AppBlockCanaryContext()).start();
//        Stetho.initializeWithDefaults(getApplicationContext());
//        UETool.showUETMenu();
        init();
    }

    public void init() {


    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

        // 安装tinker
//        Beta.installTinker();
    }

//    public static AppComponent getAppComponent() {
//
//        if (appComponent == null) {
//            appComponent = DaggerAppComponent.builder()
//                    .appModule(new AppModule(instance))
//                    .httpModule(new HttpModule())
//                    .build();
//        }
//        return appComponent;
//    }


    public static synchronized BaseApplication getInstance() {
        return instance;
    }


}
