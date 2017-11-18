package com.example.jh.data.location;


import com.example.jh.data.PerActivity;
import com.example.jh.data.entity.HistoryEntity;
import com.example.jh.data.net.HttpResultFunc;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/22.
 */
@PerActivity
public class LocationRepo {
    private LocationApi locationApi;
    @Inject
    LocationRepo(LocationApi locationApi) {
        this.locationApi = locationApi;
    }



    public Observable<LocationEntity> location(String token, String imei, String coordtype) {
        return locationApi.location(token, imei, coordtype)
                .map(new HttpResultFunc<>());
    }

    public Observable<List<HistoryEntity>> history(String token, String imei, long begin, long end, String coordtype) {
        return locationApi.history(token, imei, begin, end,coordtype)
                .map(new HttpResultFunc<>());
    }

    public Observable<LocationEntity> getloction(String token, String imei) {
        return locationApi.getloction(token, imei)
                .map(new HttpResultFunc<>());
    }
}
