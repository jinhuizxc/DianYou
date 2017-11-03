package com.example.jh.data.disturb;

import com.example.jh.data.entity.BaseEntity;
import com.example.jh.data.entity.TimeConfigEntity;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

public interface DisturbApi {

    @POST("tp/index.php/device/getdevicend")
    Observable<BaseEntity<List<TimeConfigEntity>>> getdevicend(@Query("token") String token,
                                                               @Query("imei") String imei);

    @POST("tp/index.php/device/setdevicend")
    Observable<BaseEntity<List<Integer>>> setdevicend(@Query("token") String token,
                                                      @Query("imei") String imei,
                                                      @Query("indexes") String indexes,
                                                      @Query("begins") String begins,
                                                      @Query("ends") String ends,
                                                      @Query("repeats") String repeats);

    // 添加免打扰接口 https://app.praevolo.xyz/tp/index.php/device/addDevicend
    @POST("tp/index.php/device/addDevicend")
    Observable<BaseEntity<String>> addDevicend(@Query("token") String token,
                                               @Query("imei") String imei,
                                               @Query("begin") String begin,
                                               @Query("end") String end,
                                               @Query("repeat") String repeat);

    // 修改免打扰
    @POST("tp/index.php/device/editDevicend")
    Observable<BaseEntity<String>> editDevicend(@Query("token") String token,
                                                @Query("imei") String imei,
                                                @Query("index") String index,
                                                @Query("begin") String begin,
                                                @Query("end") String end,
                                                @Query("repeat") String repeat);

    // 删除免打扰
    @POST("tp/index.php/device/delDevicend")
    Observable<BaseEntity<String>> delDevicend(@Query("token") String token,
                                               @Query("imei") String imei,
                                               @Query("indexe") String index);
}

