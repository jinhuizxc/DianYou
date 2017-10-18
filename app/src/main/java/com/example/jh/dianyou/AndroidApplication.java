package com.example.jh.dianyou;

import android.app.Application;
import android.content.Context;

import com.example.jh.dianyou.di.components.ApplicationComponent;
import com.example.jh.dianyou.di.components.DaggerApplicationComponent;
import com.example.jh.dianyou.di.modules.ApplicationModule;

/**
 * Created by Administrator on 2017/9/25.
 */

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;
    private static AndroidApplication mApplication;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化注入器
        this.initializeInjector();


        mApplication = this;
        appContext = getApplicationContext();
    }

    public AndroidApplication() {
        mApplication = this;
    }

    public static Context getAppContext() {
        return appContext;
    }


    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    // getBaseApplication
    public static AndroidApplication getBaseApplication() {
        if (mApplication != null) {
            return mApplication;
        } else {
            mApplication = new AndroidApplication();
            mApplication.onCreate();
            return mApplication;
        }
    }


}
