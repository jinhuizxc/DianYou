package com.example.jh.dianyou.features.message;

import com.example.jh.data.PerActivity;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by jinhui on 2017/12/5.
 */

@PerActivity
@Subcomponent(modules = {ActivityModule.class})
public interface MessageComponent {
    void inject (MessageActivity messageActivity);
}