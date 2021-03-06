package com.example.jh.dianyou.features.fencelist.fence;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.jh.base.YaRxPresenter;
import com.example.jh.dianyou.utils.T;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by jinhui on 2017/10/20.
 * Email：1004260403@qq.com
 */

public class FencePresenter extends YaRxPresenter<FenceView> {

    private static final String TAG = FencePresenter.class.getSimpleName();

    @Inject
    AMapLocationClientOption mLocationOption;

    @Inject
    public FencePresenter() {
    }

    // 得到我的位置
    public void getMyLocation() {
        AMapLocationClient mlocationClient = new AMapLocationClient(getView().context());
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Log.e(TAG, aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
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
    // 得到设备的位置
    public void getDeviceLocation() {
    }

    public void latlng2Address(double latitude, double longitude) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(getView().context());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            /**
             * 根据给定的经纬度和最大结果数返回逆地理编码的结果列表。
             * @param result
             * @param rCode
             */
            @Override
            public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
                Log.e("安全区域0", "rCode =" + rCode);
                if (rCode == 1000) {    // 之前是1000 ，现在怎么是1008
                    Log.e("安全区域1", "rCode =" + rCode);
                    Log.e("安全区域", "result =" + result + "," + "result.getRegeocodeAddress() =" + result.getRegeocodeAddress()
                            + "," + "result.getRegeocodeAddress().getFormatAddress() =" + result.getRegeocodeAddress().getFormatAddress());
                    if (result != null && result.getRegeocodeAddress() != null
                            && result.getRegeocodeAddress().getFormatAddress() != null) {
                        if(result.getRegeocodeAddress().getFormatAddress().equals("")) {
                            getView().showFenceAddress("没有地址");
                        }else {
                            String addressName = result.getRegeocodeAddress().getFormatAddress()
                                    + "附近";
                            getView().showFenceAddress(addressName);
                        }

                    } else {
                        T.showShort("no_result");
                    }
                } else {

                }
            }

            /**
             * 根据给定的地理名称和查询城市，返回地理编码的结果列表。
             * @param geocodeResult
             * @param i
             */
            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });

        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latitude,longitude), 200,GeocodeSearch.GPS);
        geocodeSearch.getFromLocationAsyn(query);

    }
}
