package com.example.jh.dianyou.features.adddevice;

import com.example.jh.dianyou.view.LoadDataView;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

public interface AddDeviceView extends LoadDataView {
    void showRequestView();

    void showInfoLayout();

    void dismissRequest();

    void finishAddDevice();
}