package com.example.jh.dianyou.features.fencelist.fence;

import com.example.jh.data.device.DeviceEntity;
import com.example.jh.data.fence.FenceEntity;
import com.example.jh.dianyou.view.LoadDataView;

import java.util.List;

/**
 * Created by jinhui on 2017/10/20.
 * Email：1004260403@qq.com
 */

public interface FenceView extends LoadDataView {

    void showFenceName(String name);

    void showFenceDevice(String device);

    void showFenceAddress(String address);

    void showFenceLocation(FenceEntity fenceEntity);

    void showFenceRadius(String radius);

    void showLocation(double latitude, double longitude);

    void changeCheckedDevice(List<DeviceEntity> list);

    void getCheckedDeviceImei(String checkedImei);

    void addFendNext();

    void addOnMap(double lat, double lng);
}

