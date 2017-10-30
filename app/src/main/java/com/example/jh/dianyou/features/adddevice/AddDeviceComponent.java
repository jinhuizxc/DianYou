package com.example.jh.dianyou.features.adddevice;

import com.example.jh.data.PerActivity;
import com.example.jh.data.device.DeviceModule;
import com.example.jh.data.location.LocationModule;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

@PerActivity
@Subcomponent(modules = {ActivityModule.class, LocationModule.class, DeviceModule.class})
public interface AddDeviceComponent {
    void inject(AddDeviceActivity addDeviceActivity);
}
