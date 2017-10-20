package com.example.jh.dianyou.features.fencelist.fence;



import com.example.jh.data.PerActivity;
import com.example.jh.data.fence.FenceModule;
import com.example.jh.data.location.LocationModule;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by fflamingogo on 2016/7/22.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, FenceModule.class, LocationModule.class})
public interface FenceComponent {
    void inject(FenceActivity fenceActivity);
}
