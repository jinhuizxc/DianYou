package com.example.jh.dianyou.features.talk;

import com.example.jh.data.PerActivity;
import com.example.jh.data.device.DeviceModule;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by jinhui on 2017/12/5.
 */

@PerActivity
@Subcomponent(modules = {ActivityModule.class, DeviceModule.class})
public interface TalkComponent{
    void inject(TalkActivity talkActivity);
}
