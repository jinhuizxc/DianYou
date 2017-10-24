package com.example.jh.dianyou.features.local;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Printer;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;
import com.example.jh.base.YaRxPresenter;
import com.example.jh.data.ProgressSubscriber;
import com.example.jh.data.SubscriberOnNextListener;
import com.example.jh.data.location.LocationEntity;
import com.example.jh.dianyou.loading.DialogThridUtils;
import com.example.jh.dianyou.loading.WeiboDialogUtils;
import com.example.jh.dianyou.utils.PreferencesUtils;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jinhui on 2017/10/18.
 * Email：1004260403@qq.com
 */

public class LocalPresenter extends YaRxPresenter<LocalView> {


    private static final String TAG = LocalPresenter.class.getSimpleName();

    private Dialog mDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DialogThridUtils.closeDialog(mDialog);
                    break;
            }
        }
    };
    @Inject
    AMapLocationClientOption mLocationOption;
    @Inject
    public LocalPresenter() {

    }


    public void getDeviceLocation() {

    }

    public void getDevice() {
    }

    public void getLocation() {

    }


    public void getMylocation() {
        // 加载进度条画面
        mDialog = DialogThridUtils.showWaitDialog(getView().context(), "加载中...", false, true);
        mHandler.sendEmptyMessageDelayed(1, 1000);
        AMapLocationClient mlocationClient = new AMapLocationClient(getView().context());
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(final AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Log.e(TAG, "aMapLocation.getErrorCode() =" + aMapLocation.getErrorCode());
                        getView().showLocation(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                        System.out.println("/////" + aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }

                }
            }

        });

        mlocationClient.startLocation();
    }
}
