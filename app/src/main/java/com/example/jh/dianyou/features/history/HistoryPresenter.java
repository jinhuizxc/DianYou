package com.example.jh.dianyou.features.history;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.jh.base.YaRxPresenter;
import com.example.jh.data.PerActivity;
import com.example.jh.data.ProgressSubscriber;
import com.example.jh.data.SubscriberOnNextListener;
import com.example.jh.data.entity.HistoryEntity;
import com.example.jh.data.location.LocationEntity;
import com.example.jh.data.location.LocationRepo;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.utils.PreferencesUtils;
import com.example.jh.dianyou.utils.T;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by fflamingogo on 2016/7/22.
 *
 * Error:(42, 8) 错误:
 * [com.example.jh.dianyou.features.history.HistoryComponent.inject(com.example.jh.dianyou.features.history.HistoryActivity)]
 * com.example.jh.data.location.LocationApi cannot be provided without an @Provides-annotated method.
 com.example.jh.data.location.LocationApi is injected at
 com.example.jh.data.location.LocationRepo.<init>(locationApi)
 com.example.jh.data.location.LocationRepo is injected at
 com.example.jh.dianyou.features.history.HistoryPresenter.<init>(locationRepo)
 com.example.jh.dianyou.features.history.HistoryPresenter is injected at
 com.example.jh.base.YaMvpDiActivity.mPresenter
 com.example.jh.dianyou.features.history.HistoryActivity is injected at
 com.example.jh.dianyou.features.history.HistoryComponent.inject(historyActivity)



 Error:(7, 44) 错误: 找不到符号
 符号:   类 DaggerApplicationComponent
 位置: 程序包 com.example.jh.dianyou.di.components
 */
@PerActivity
public class HistoryPresenter extends YaRxPresenter<HistoryView> {

    private static final String TAG = HistoryPresenter.class.getSimpleName();
    @Inject
    AMapLocationClientOption mLocationOption;

    private LocationRepo locationRepo;
//    private DeviceRepo deviceRepo;

    private long mStartTime;
    private long mEndTime;
    GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
    private String mImei = "200030004000020";

//    @Inject
//    HistoryPresenter(LocationRepo locationRepo, DeviceRepo deviceRepo) {
//        this.locationRepo = locationRepo;
//        this.deviceRepo = deviceRepo;
//    }

    @Inject
    HistoryPresenter(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    void showHistory() {
//        mImei = deviceRepo.getCheckedDeviceImei();
        GregorianCalendar today = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));


        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        int hour = today.get(Calendar.HOUR_OF_DAY);
        int min = today.get(Calendar.MINUTE);
        GregorianCalendar startTime = new GregorianCalendar(year, month, day, 0, 0, 0);
        startTime.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        GregorianCalendar endTime = new GregorianCalendar(year, month, day, hour, min, 0);
        endTime.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mStartTime = startTime.getTimeInMillis() / 1000;
        mEndTime = endTime.getTimeInMillis() / 1000;
        getView().showDate(year, month + 1, day);
        System.out.println("---" + mStartTime + "-" + "" + mEndTime);
        // 加载历史轨迹
        loadHistory();

        getView().setTimeProgress(hour * 60 + min);


    }

    void previous() {
        mStartTime = mStartTime - 86400;
        mEndTime = mStartTime + 86400;
        System.out.println("---" + mStartTime + "-" + "" + mEndTime);
        // fgdg
        gregorianCalendar.setTimeInMillis(mStartTime * 1000);
        int year = gregorianCalendar.get(Calendar.YEAR);
        int month = gregorianCalendar.get(Calendar.MONTH);
        int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        getView().showDate(year, month + 1, day);
        getView().resetProgress();
        // 删除历史轨迹
        deleteLoadHistory();
    }

    void next() {
        mStartTime = mStartTime + 86400;
        mEndTime = mStartTime + 86400;
        System.out.println("---" + mStartTime + "-" + "" + mEndTime);
        gregorianCalendar.setTimeInMillis(mStartTime * 1000);
        int year = gregorianCalendar.get(Calendar.YEAR);
        int month = gregorianCalendar.get(Calendar.MONTH);
        int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        getView().showDate(year, month + 1, day);
        getView().resetProgress();
        // 删除历史轨迹
//        delateLoadHistory();
    }

    void changeMin(int progress) {
        mEndTime = mStartTime + progress * 60;
        System.out.println("---" + mStartTime + "-" + "" + mEndTime);
    }


    private SubscriberOnNextListener subscriberOnNextListener = new SubscriberOnNextListener<List<HistoryEntity>>() {


        @Override
        public void onNext(List<HistoryEntity> locations) {
            if (locations.size() == 0) {
                T.showShort(getView().context().getString(R.string.desc_no_history));
                getMyLocation();
                return;
            }
            getView().addOnMap(locations);
            Log.e(TAG, "locations =" + locations);

        }

    };

    public void selectDate(int year, int month, int day) {
        GregorianCalendar startTime = new GregorianCalendar(year, month, day, 0, 0, 0);
        startTime.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mStartTime = startTime.getTimeInMillis() / 1000;
        GregorianCalendar endTime = new GregorianCalendar(year, month, day, 24, 0, 0);
        endTime.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mEndTime = endTime.getTimeInMillis() / 1000;
        //  加载历史轨迹
//        loadHistory();
        System.out.println("---" + mStartTime + "-" + "" + mEndTime);
        getView().showDate(year, month + 1, day);
    }

    // 加载历史轨迹
    void loadHistory() {
//        getView().clearMap();
//        addUtilDestroy(locationRepo.history(PreferencesUtils.getString(getView().context(), "Token"), mImei, mStartTime, mEndTime, "gcj02")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new ProgressSubscriber(getView().context(), subscriberOnNextListener))
//        );

        // 模拟一次历史轨迹
        // https://app.imerit.cn/tp/index.php/Location/history_fast?
        // token=3980e45ed8664852ee40000054ff0e7d & imei= 200030004000020 & begin= 1510934400 & end=1510978080& coordtype=gcj02
        String Token = "3980e45ed8664852ee40000054ff0e7d";
        getView().clearMap();
        addUtilDestroy(locationRepo.history(Token, mImei, mStartTime, mEndTime, "gcj02")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ProgressSubscriber(getView().context(), subscriberOnNextListener))
        );
    }

    // 删除加载的历史轨迹？
    public void deleteLoadHistory() {
//        getView().clearMap();
//        addUtilDestroy(locationRepo.history(PreferencesUtils.getString(getView().context(), "Token"), mImei, mStartTime, mEndTime, "gcj02")
//                .delay(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new DefaultSubscriber<List<LocationEntity>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<LocationEntity> locations) {
//                        if (locations.size() == 0) {
//                            T.showShort(getView().context().getString(R.string.desc_no_history));
//                            getMyLocation();
//                            return;
//                        }
//                        getView().addOnMap(locations);
//                    }
//                })
//
//        );
    }


    void getMyLocation() {
        AMapLocationClient mlocationClient = new AMapLocationClient(getView().context());
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        /*aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取经度信息*/
                        getView().showLocation(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                        Log.d(TAG, "onLocationChanged: " + aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo() + "              HistoryPresenter");
                    }
                }
            }
        });
        //启动定位
        mlocationClient.startLocation();
    }

}
