package com.example.jh.dianyou.features.local;



import com.example.jh.data.PerActivity;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/3/27.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class})
public interface LocalComponent {
    void inject(LocalActivity localActivity);

//    void inject(LocalFragment localFragment);
}
