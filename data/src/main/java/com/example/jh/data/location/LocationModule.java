package com.example.jh.data.location;



import com.example.jh.data.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by fflamingogo on 2016/7/22.
 */
@Module
public class LocationModule {

    @PerActivity
    @Provides
    LocationApi provideLocationApi(final Retrofit retrofit) {
        return retrofit.create(LocationApi.class);
    }
}
