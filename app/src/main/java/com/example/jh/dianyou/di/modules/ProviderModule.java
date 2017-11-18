package com.example.jh.dianyou.di.modules;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
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
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;

import static com.example.jh.data.net.HttpMethods.BASE_URL;


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
    OkHttpClient provideHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(1 * 60, TimeUnit.SECONDS);
        builder.readTimeout(1 * 60, TimeUnit.SECONDS);
        builder.writeTimeout(1 * 60, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request(); //Current Request

                Response response = chain.proceed(originalRequest); //Get response of the request

                /** DEBUG STUFF */
                if (BuildConfig.DEBUG) {
                    //I am logging the response body in debug mode. When I do this I consume the response (OKHttp only lets you do this once) so i have re-build a new one using the cached body
                    String bodyString = response.body().string();
                    System.out.println(String.format("Sending request %s with headers %s ", originalRequest.url(), originalRequest.headers()));
                    System.out.println(String.format("Got response HTTP %s %s \n\n with body %s \n\n with headers %s ", response.code(), response.message(), bodyString, response.headers()));
                    response = response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
                }

                return response;
            }
        });

        return builder.build();
    }

    // 配置
    @Singleton
    @Provides
    Retrofit provideRetrofit(final OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
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
