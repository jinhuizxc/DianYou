package com.example.jh.dianyou.features.local;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Printer;
import android.widget.Toast;

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
//    private DeviceRepo deviceRepo;

    private ProgressDialog pd;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    pd.dismiss();
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
        Log.e(TAG, "getDevice 方法执行");
        showMessageNum();
    }

    // 获取信息数量
    private void showMessageNum() {
//        addUtilDestroy(
//                messageRepo.getMessageCount()
//                        .subscribe(new DefaultSubscriber<Integer>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(Integer integer) {
//                                getView().showMessageRead(integer.intValue());
//                                Log.e(TAG, "获取消息红点数量 =" + integer.intValue());
//                            }
//                        })
//        );
        getView().showMessageRead(120);
    }

    public void getLocation() {

    }


    public void getMylocation() {
        // 加载进度条画面
        // 加载进度条
        pd = new ProgressDialog(getView().context());
        pd.setMessage("loading");
        if (!pd.isShowing()) {
            pd.show();
        }
        handler.sendEmptyMessageDelayed(1,1000);
//        mDialog = DialogThridUtils.showWaitDialog(getView().context(), "加载中...", false, true);
//        mHandler.sendEmptyMessageDelayed(1, 1000);
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
                        Toast.makeText(getView().context(), "网络连接异常，请检查网络是否连接！", Toast.LENGTH_SHORT).show();
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

    public void addDevice() {
//        int deviceCount = deviceRepo.getDeviceCount();
//        getView().addDeviceStatus(deviceCount);
        getView().addDeviceStatus(1);  // 测试0 或者1
    }

    public void checkDevice(String imei) {
//        deviceRepo.checkDevice(imei);
    }

    public void manager() {
        getView().tiaozhaun();
    }
}
