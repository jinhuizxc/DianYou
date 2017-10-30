package com.example.jh.dianyou.features.adddevice;

import com.example.jh.base.YaRxPresenter;
import com.example.jh.dianyou.utils.PreferencesUtils;

import javax.inject.Inject;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

public class AddDevicePresenter extends YaRxPresenter<AddDeviceView> {

    private static final String TAG = AddDevicePresenter.class.getSimpleName();
//    private DeviceRepo deviceRepo;
    private boolean isBind = false;

    @Inject
    public AddDevicePresenter() {
    }

    public void bindDevice(String imei, String nick) {
        final String token = PreferencesUtils.getString(getView().context(), "Token");
    }
}
