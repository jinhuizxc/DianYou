package com.example.jh.data.disturb;

import com.example.jh.data.entity.TimeConfigEntity;
import com.example.jh.data.net.HttpResultFunc;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

public class DisturbRepo {

    private DisturbApi disturbApi;

    @Inject
    DisturbRepo(DisturbApi disturbApi) {
        this.disturbApi = disturbApi;
    }

    public Observable<List<TimeConfigEntity>> getDevicend(String token, String imei) {
        return disturbApi.getdevicend(token,imei).map(new HttpResultFunc<>());
    }

    public Observable<List<Integer>> setDevicend(String token,String imei,String index,String begin,String end,String repeat) {
        return disturbApi.setdevicend(token, imei, index, begin, end, repeat).map(new HttpResultFunc<>());
    }

    public Observable<String> addDevicend(String token, String imei, String begin, String end, String repeat) {
        return disturbApi.addDevicend(token,imei,begin,end,repeat)
                .map(new HttpResultFunc<>());
    }

    public Observable<String> delDevicend(String token,String imei,String index) {
        return disturbApi.delDevicend(token,imei,index).map(new HttpResultFunc<>());
    }

    public Observable<String> editDevicend(String token,String imei,String index,String begin,String end,String repeat) {
        return disturbApi.editDevicend(token, imei, index, begin, end, repeat).map(new HttpResultFunc<>());
    }

}