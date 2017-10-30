package com.example.jh.data.alarmclock;


import com.example.jh.data.net.HttpResultFunc;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2017/5/16.
 */

public class AlarmClockRepo {
    private AlarmClockApi alarmClockApi;
    @Inject
    AlarmClockRepo(AlarmClockApi alarmClockApi){
        this.alarmClockApi = alarmClockApi;
    }

    public Observable<String> clockAdd (String token, String imei, String about, String begin, String repeat){
        return alarmClockApi.clockAdd(token, imei, about, begin, repeat)
                .map(new HttpResultFunc<>());
    }

    public Observable<String> clockDel(String tokekn, String imei, String index){
        return alarmClockApi.clockDel(tokekn, imei, index)
                .map(new HttpResultFunc<>());
    }

    public Observable<List<AlarmConfigEntity>> clocklist(String token, String imei){
        return alarmClockApi.clockList(token, imei)
                .map(new HttpResultFunc<>());
    }

    public Observable<String> clockEdit (String token, String imei, String index, String begin, String about, String repeat){
        return alarmClockApi.clockEdit(token, imei, index, begin, about, repeat)
                .map(new HttpResultFunc<>());
    }
}
