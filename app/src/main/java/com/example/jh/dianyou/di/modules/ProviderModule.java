package com.example.jh.dianyou.di.modules;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.example.jh.dianyou.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Administrator on 2017/2/28.
 */
@Module
public class ProviderModule {

    private static final boolean DEBUG = "debug".equals(BuildConfig.BUILD_TYPE);


    @Singleton
    @Provides
    Gson provideGson() {
        final GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }


    @Singleton
    @Provides
    AMapLocationClient provideAMapLocationClient(Context context, AMapLocationClientOption mLocationOption) {
        AMapLocationClient mlocationClient = new AMapLocationClient(context);
        mlocationClient.setLocationOption(mLocationOption);
        return mlocationClient;
    }

    @Singleton
    @Provides
    AMapLocationClientOption providAMapLocationClientOption() {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);

        return mLocationOption;
    }
}
