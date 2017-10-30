package com.example.jh.data.alarmclock;



import com.example.jh.data.entity.BaseEntity;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/18.
 */

public interface AlarmClockApi {
    // 添加闹钟
    @POST("tp/index.php/device/clockAdd")
    Observable<BaseEntity<String>> clockAdd(
            @Query("token") String token, @Query("imei") String imei,
            @Query("about") String about, @Query("begin") String begin,
            @Query("repeat") String repeat);

    //获取闹钟列表
    @POST("tp/index.php/device/clockList")
    Observable<BaseEntity<List<AlarmConfigEntity>>> clockList(@Query("token") String token, @Query("imei") String imei);

    //删除闹钟
    @POST("tp/index.php/device/clockDel")
    Observable<BaseEntity<String>> clockDel(
            @Query("token") String token, @Query("imei") String imei,
            @Query("index") String index);

    //编辑闹钟
    @POST("tp/index.php/device/clockEdit")
    Observable<BaseEntity<String>> clockEdit(
            @Query("token") String token, @Query("imei") String imei,
            @Query("index") String index, @Query("begin") String begin,
            @Query("about") String about, @Query("repeat") String repeat);
}
