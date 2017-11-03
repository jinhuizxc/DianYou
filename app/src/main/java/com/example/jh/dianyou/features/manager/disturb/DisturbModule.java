package com.example.jh.dianyou.features.manager.disturb;

import com.example.jh.data.PerActivity;
import com.example.jh.data.disturb.DisturbApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

@Module
public class DisturbModule {
    @PerActivity
    @Provides
    DisturbApi disturbApi(final Retrofit retrofit) {
        return retrofit.create(DisturbApi.class);
    }
}