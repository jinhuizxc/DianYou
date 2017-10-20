package com.example.jh.dianyou.features.local;

import android.util.Log;

import com.amap.api.maps2d.model.LatLng;
import com.example.jh.base.YaRxPresenter;
import com.example.jh.data.ProgressSubscriber;
import com.example.jh.data.SubscriberOnNextListener;
import com.example.jh.data.location.LocationEntity;
import com.example.jh.dianyou.utils.PreferencesUtils;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui on 2017/10/18.
 * Email：1004260403@qq.com
 */

public class LocalPresenter extends YaRxPresenter<LocalView> {



    @Inject
    public LocalPresenter() {

    }


    public void getDeviceLocation() {

    }
}
