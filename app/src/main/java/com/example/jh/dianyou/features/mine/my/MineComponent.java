package com.example.jh.dianyou.features.mine.my;



import com.example.jh.data.PerActivity;

import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/3/29.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class})
public interface MineComponent {
    void inject(MineActivity mineActivity);
}
