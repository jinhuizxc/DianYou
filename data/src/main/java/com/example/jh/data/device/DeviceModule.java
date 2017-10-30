package com.example.jh.data.device;



import com.example.jh.data.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by fflamingogo on 2016/7/22.
 */
@Module
public class DeviceModule {


    @PerActivity
    @Provides
    DeviceApi provideDeviceApi(final Retrofit retrofit) {
        return retrofit.create(DeviceApi.class);
    }

    /**
     * 数据库的部分待分析 2017-10-30
     */
//    @PerActivity
//    @Provides
//    DeviceDelegate provideDbUserDelegate(final DeviceDelegateImpl deviceDelegate) {
//        return deviceDelegate;
//    }
}
