package com.example.jh.data.location;



import com.example.jh.data.entity.BaseEntity;
import com.example.jh.data.entity.HistoryEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface LocationApi {
    @GET("tp/index.php/Location/history_fast")
    Observable<BaseEntity<List<HistoryEntity>>> history(@Query("token") String token, @Query("imei") String imei, @Query("begin") long begin, @Query("end") long end, @Query("coordtype") String coordtype);

    @GET("tp/index.php/Location/Location")
    Observable<BaseEntity<LocationEntity>> location(@Query("token") String token, @Query("imei") String imei, @Query("coordtype") String coordtype);

    @POST("tp/index.php/Location/getloction")
    Observable<BaseEntity<LocationEntity>> getloction(@Query("token") String token, @Query("imei") String imei);
}
