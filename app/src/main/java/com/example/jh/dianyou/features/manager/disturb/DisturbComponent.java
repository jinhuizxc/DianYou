package com.example.jh.dianyou.features.manager.disturb;

import com.example.jh.data.PerActivity;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

@PerActivity
@Subcomponent(modules = {ActivityModule.class,DisturbModule.class})
public interface DisturbComponent {
    void inject(DisturbActivity disturbActivity);
}
