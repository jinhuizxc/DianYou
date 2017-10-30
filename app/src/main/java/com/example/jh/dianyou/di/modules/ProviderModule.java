package com.example.jh.dianyou.di.modules;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.example.jh.data.entity.DbOpenHelper;
import com.example.jh.dianyou.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Administrator on 2017/2/28.
 */
@Module
public class ProviderModule {

    private static final boolean DEBUG = "debug".equals(BuildConfig.BUILD_TYPE);

    /**
     * 2017-10-30 数据库的module 会不会影响运行？
     * @param dbOpenHelper
     * @return
     */
    @Singleton
    @Provides
    public BriteDatabase provideBriteDb(DbOpenHelper dbOpenHelper) {
        final BriteDatabase briteDb =
                SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, AndroidSchedulers.mainThread());
        briteDb.setLoggingEnabled(true);
        return briteDb;
    }

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
