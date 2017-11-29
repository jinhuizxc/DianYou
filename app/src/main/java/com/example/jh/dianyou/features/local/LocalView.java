package com.example.jh.dianyou.features.local;



import com.example.jh.dianyou.view.LoadDataView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface LocalView extends LoadDataView {

    // 设备列表这块要用数据库
//    void showDeviceList(List<DeviceEn> deviceEns);

    void noDevice();

    void setDeviceName(String name);


    void setAddress(String address);
    void setTime(String s);
    void initLocation();

    void addDeviceStatus(int count);

    void setPower(int power);

    void tiaozhaun(String imei);

    void tiaozhaun();

    void showRead(int count);
    // 显示信息
    void showMessageRead(int count);
    void setPhoneNumber(String phone);

    void showCallResult();

    void showTakePhotoResult();

    void addlocationMap(double lat, double lng);

    void showLocation(double latitude, double longitude);
    void showDeviceLocation(double latitude, double longitude);

}
