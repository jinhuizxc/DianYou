package com.example.jh.dianyou.features.manager;

import com.example.jh.data.PerActivity;
import com.example.jh.data.device.DeviceModule;
import com.example.jh.data.fence.FenceModule;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

@PerActivity
@Subcomponent(modules = {ActivityModule.class, DeviceModule.class, FenceModule.class})
public interface ManagerComponent {
    void inject(ManagerActivity managerActivity);
}
