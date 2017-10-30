package com.example.jh.data.alarmclock;



import com.example.jh.data.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/5/16.
 */

@Module
public class AlarmClockModule {
    @PerActivity
    @Provides
    AlarmClockApi alarmClockApi(final Retrofit retrofit){
        return retrofit.create(AlarmClockApi.class);
    }
}
