package com.leifu.acomponentdemo;


import com.leifu.acomponentdemo.di.component.AppComponent;
import com.leifu.acomponentdemo.di.component.DaggerAppComponent;
import com.leifu.commonlib.app.BaseApplication;
import com.leifu.commonlib.di.module.AppModule;
import com.leifu.commonlib.di.module.HttpModule;

/**
 * 创建人:雷富
 * 创建时间:2019/2/19 17:32
 * 描述:
 */
public class App extends BaseApplication {

    private static AppComponent appComponent;

    @Override
    public void init() {
        super.init();

    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(BaseApplication.getInstance()))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
